package quaternary.incorporeal.feature.corporetics.entity;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import quaternary.incorporeal.core.etc.helper.EtcHelpers;
import quaternary.incorporeal.feature.corporetics.item.CorporeticsItems;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileOpenCrate;
import vazkii.botania.common.core.helper.MathHelper;

import java.util.List;
import java.util.UUID;

public class EntityFracturedSpaceCollector extends Entity {
	public EntityFracturedSpaceCollector(World world) {
		super(world);
	}
	
	public EntityFracturedSpaceCollector(World world, BlockPos cratePos, EntityPlayer player) {
		this(world);
		
		this.cratePos = cratePos;
		this.ownerUUID = player.getUniqueID();
	}
	
	private BlockPos cratePos = BlockPos.ORIGIN;
	
	public static void createDataParamaters() {
		DATA_AGE = EntityDataManager.createKey(EntityFracturedSpaceCollector.class, DataSerializers.VARINT);
	}
	
	private static DataParameter<Integer> DATA_AGE;
	private UUID ownerUUID;
	
	private static final double RADIUS = 2;
	private static final int MAX_AGE = 30;
	private static final float AGE_SPECIAL_START = MAX_AGE * 3f / 4f;
	private static final int PARTICLE_COUNT = 12;
	private static final int MANA_COST_PER_ITEM = 500; //TODO balance this?
	
	@Override
	protected void entityInit() {
		setNoGravity(true);
		setEntityInvulnerable(true);
		isImmuneToFire = true;
		width = 0;
		height = 0;
		
		dataManager.register(DATA_AGE, 0);
	}
	
	@Override
	public void onUpdate() {
		motionX = 0;
		motionY = 0;
		motionZ = 0;
		super.onUpdate();
		
		int age = dataManager.get(DATA_AGE);
		age++;
		dataManager.set(DATA_AGE, age);
		
		if(world.isRemote && age <= MAX_AGE) {
			double ageFraction = age / (double) MAX_AGE;
			//double radiusMult = 4 * (ageFraction - ageFraction * ageFraction); //simple and cute easing function
			double radiusMult = 1.6 * (ageFraction - Math.pow(ageFraction, 7)); //less simple but cuter easing function
			double particleAngle = age / 25d;
			double height = radiusMult / 2;
			
			for(int i = 0; i < PARTICLE_COUNT; i++, particleAngle += (2 * Math.PI) / PARTICLE_COUNT) {
				double x = Math.cos(particleAngle) * RADIUS * radiusMult;
				double z = Math.sin(particleAngle) * RADIUS * radiusMult;
				
				float size = (float) (1 + ageFraction * 5 * Math.random());
				
				Botania.proxy.sparkleFX(posX + x, posY + height, posZ + z, 0.9f, 0.15f, 0.1f, size, 5);
			}
			
			double x = Math.cos(Math.random() * Math.PI * 2) * RADIUS * radiusMult;
			double z = Math.cos(Math.random() * Math.PI * 2) * RADIUS * radiusMult;
			
			Botania.proxy.wispFX(posX + x, posY - 0.5 + height, posZ + z, 1f, .5f, 0f, .3f, -.3f, 0.5f);
			
			if(age >= MAX_AGE - 2) {
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY, posZ, 0, 0, 0);
				for(int i = 0; i < 5; i++) {
					Botania.proxy.wispFX(posX, posY, posZ, 0.9f, 0.45f, 0.05f, 2f, -.1f, 0.1f);
				}
			}
		} else {
			if(age > AGE_SPECIAL_START) {
				AxisAlignedBB aabb = new AxisAlignedBB(posX - RADIUS, posY - 1, posZ - RADIUS, posX + RADIUS, posY + 1, posZ + RADIUS);
				List<EntityItem> nearbyItemEnts = world.getEntitiesWithinAABB(EntityItem.class, aabb, (ent) -> {
					return ent != null && MathHelper.pointDistancePlane(ent.posX, ent.posZ, posX, posZ) <= RADIUS;
				});
				
				//Succ into the wormhole
				for(EntityItem ent : nearbyItemEnts) {
					double xDifference = posX - ent.posX;
					double zDifference = posZ - ent.posZ;
					
					//play with this setting
					ent.motionX += (xDifference * .3);
					ent.motionZ += (zDifference * .3);
					
					ent.velocityChanged = true;
				}
				
				if(age >= MAX_AGE) {
					//Transport the items
					//first figure out who to take the mana from
					if(ownerUUID == null) {
						setDead();
						return;
					}
					
					EntityPlayer player = world.getPlayerEntityByUUID(ownerUUID);
					
					if(player == null) {
						setDead();
						return;
					}
					
					//fuckit let's just load the chunk
					IBlockState state = world.getBlockState(cratePos);
					TileEntity tile = world.getTileEntity(cratePos);
					
					if(tile != null && EtcHelpers.isOpenCrate(state, tile)) {
						TileOpenCrate crate = (TileOpenCrate) tile;
						if(crate.canEject()) {
							//This is kinda weird but let's emulate the way the open crate itself looks for redstone
							boolean redstone = false;
							for(EnumFacing whichWay : EnumFacing.values()) {
								if(world.getRedstonePower(cratePos.offset(whichWay), whichWay) != 0) {
									redstone = true;
									break;
								}
							}
							
							ItemStack toolStack = new ItemStack(CorporeticsItems.FRACTURED_SPACE_ROD);
							
							//Crates only have 1 slot inventory so I have to manually dump the items one by one
							for(EntityItem ent : nearbyItemEnts) {
								ItemStack stack = ent.getItem/*Stack*/();
								
								int costForThisStack = MANA_COST_PER_ITEM * stack.getCount();
								if(ManaItemHandler.requestManaExact(toolStack, player, costForThisStack, false)) {
									ManaItemHandler.requestManaExact(toolStack, player, costForThisStack, true);
									crate.eject(stack, redstone);
									ent.setDead();
								}
							}
						}
					}
					
					//My work here is done
					setDead();
				}
			}
		}
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		dataManager.set(DATA_AGE, nbt.getInteger("Age"));
		cratePos = NBTUtil.getPosFromTag(nbt.getCompoundTag("CratePos"));
		ownerUUID = NBTUtil.getUUIDFromTag(nbt.getCompoundTag("Owner"));
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("Age", dataManager.get(DATA_AGE));
		nbt.setTag("CratePos", NBTUtil.createPosTag(cratePos));
		nbt.setTag("Owner", NBTUtil.createUUIDTag(ownerUUID));
	}
	
	@Override
	public int getMaxInPortalTime() {
		return Integer.MAX_VALUE; //Nope!
	}
	
	@Override
	public EnumPushReaction getPushReaction() {
		return EnumPushReaction.IGNORE;
	}
}
