package tuhljin.automagy.api.example.minefactoryreloaded;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;
import tuhljin.automagy.api.inventarium.IInventariumContentsProvider;

/** Provides Inventarium support for blocks such as Jabba's barrels. */
public class ProviderForDeepStorage implements IInventariumContentsProvider {

	public static final int PRIORITY = 400;

	@Override
	public boolean canHandleTile(TileEntity te) {
		return te instanceof IDeepStorageUnit;
	}

	@Override
	public int getSlotCount(TileEntity te, int setID) {
		IDeepStorageUnit dsu = (IDeepStorageUnit) te;
		int max = dsu.getMaxStoredCount();
		ItemStack stack = dsu.getStoredItemType();
		int maxPerSlot = stack == null ? 64 : stack.getMaxStackSize();
		return maxPerSlot > 0 ? MathHelper.ceiling_double_int(max / (double)maxPerSlot) : 0;
	}

	@Override
	public ItemStack[] getContents(TileEntity te, int setID) {
		ItemStack stack = ((IDeepStorageUnit)te).getStoredItemType();
		return new ItemStack[] { stack };
	}

}
