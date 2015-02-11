package tuhljin.automagy.api.nethermind;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;


public interface INetherRune {

	/** Returns an integer that will be used to identify the type of rune. These types are used in subsequent calls to
	 *  the class to get the block created, essentia cost, etc., and so should identify to that block class what the rune
	 *  is, but they don't have to be unique in relation to other blocks since they'll use their own set of integers.
	 *  Should generally return zero or greater; those with less than zero are considered "basic" runes and are treated
	 *  differently. The return could be the same each time or based on metadata or even random. */
	int getRuneType(IBlockAccess blockaccess, int x, int y, int z);

	/** Returns the block to be created. */
	Block blockCreated(int runeType);

	/** Returns the metadata of the block to be created. */
	int blockCreatedMetadata(int runeType);

	/** Return how many ticks until the block is created (after essentia is consumed). If less than one, no block will be
	 *  created. */
	int ticksUntilBlockCreated(int runeType);

	/** Return the essentia cost of creating a block. */
	AspectList essentiaCostPerBlockCreated(int runeType);

	/** This is called when this rune is going to be used. Use it to change its appearance (e.g. adjust its metadata).
	 *  If getRuneType returned a negative number, this won't be called. */
	void activateRune(World world, int x, int y, int z, int runeType);

	/** This is called when this rune is no longer being used. Use it to change its appearance (e.g. adjust its metadata).
	 *  If getRuneType returned a negative number, this won't be called. */
	void deactivateRune(World world, int x, int y, int z, int runeType);

	/** Return a RuneCategory to indicate special behavior or null for standard behavior. Currently only used to indicate
	 *  a rune is a speed rune. */
	RuneCategory getRuneCategory(int runeType);
}
