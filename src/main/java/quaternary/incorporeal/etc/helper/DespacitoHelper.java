package quaternary.incorporeal.etc.helper;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.event.world.NoteBlockEvent.Instrument;

import java.util.Map;

//Haha yes type safety very good
@SuppressWarnings("unchecked")
public final class DespacitoHelper {
	private DespacitoHelper() {}
	
	private static Map[] despacito;
	
	private static final int[] EMPTY = new int[0];
	public static int[] getNotesForTick(int tick, Instrument inst) {
		Map yeet = despacito[tick % despacito.length];
		if(yeet == null) return EMPTY;
		
		if(yeet.containsKey(inst)) return (int[]) yeet.get(inst);
		else return EMPTY;
	}
	
	public static NoteBlockEvent.Instrument getInstrumentFromState(IBlockState state) {
		Material m = state.getMaterial();
		Instrument ret = Instrument.PIANO;
		if(m == Material.ROCK) ret = Instrument.BASSDRUM;
		if(m == Material.SAND) ret = Instrument.SNARE;
		if(m == Material.GLASS) ret = Instrument.CLICKS;
		if(m == Material.WOOD) ret = Instrument.BASSGUITAR;
		
		Block b = state.getBlock();
		if(b == Blocks.CLAY) return Instrument.FLUTE;
		if(b == Blocks.GOLD_BLOCK) return Instrument.BELL;
		if(b == Blocks.WOOL) return Instrument.GUITAR;
		if(b == Blocks.PACKED_ICE) return Instrument.CHIME;
		if(b == Blocks.BONE_BLOCK) return Instrument.XYLOPHONE;
						
		return ret;
	}
	
	public static void init() {
		despacito = new Map[]{
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.BASSDRUM, new int[]{10, 6}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSDRUM, new int[]{10}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.BASSDRUM, new int[]{6}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{10}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.BASSDRUM, new int[]{6}),
						null,
						null,
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}),
						null,
						null,
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{0}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{1}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{5}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{3}, Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.BASSDRUM, new int[]{6}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}, Instrument.BASSGUITAR, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{10}),
						null,
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						null,
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{15}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{12}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{15}),
						null,
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{10, 6}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.BASSDRUM, new int[]{6}, Instrument.SNARE, new int[]{8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{7}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8, 22}, Instrument.BASSGUITAR, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.BASSDRUM, new int[]{10, 6}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{7}, Instrument.BASSDRUM, new int[]{10, 6}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}, Instrument.SNARE, new int[]{8, 22}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{12}),
						null,
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{1}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{5}),
						null,
						ImmutableMap.of(Instrument.FLUTE, new int[]{15}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{12}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{15}),
						null,
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{8}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}, Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{10, 6}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{13}, Instrument.SNARE, new int[]{22, 8}, Instrument.BASSGUITAR, new int[]{15}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{12}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{3}),
						ImmutableMap.of(Instrument.FLUTE, new int[]{10}),
						ImmutableMap.of(Instrument.SNARE, new int[]{22}),
						ImmutableMap.of(Instrument.SNARE, new int[]{8}),
						ImmutableMap.of(Instrument.BASSDRUM, new int[]{6, 10}, Instrument.BASSGUITAR, new int[]{7}),
						null,
						ImmutableMap.of(Instrument.SNARE, new int[]{8, 22}, Instrument.BASSGUITAR, new int[]{10}),
						null
		};
	}
}
