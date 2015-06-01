package tuhljin.automagy.api.example.storagedrawers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tuhljin.automagy.api.inventarium.IInventariumContentsProvider;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup;
import com.jaquadro.minecraft.storagedrawers.api.storage.IFractionalDrawer;

public class ProviderForStorageDrawers implements IInventariumContentsProvider {

	public static final int PRIORITY = 120;

	private static ItemStack dummyItemOnePerStack = new ItemStack(Items.wooden_sword);

	@Override
	public boolean canHandleTile(TileEntity te) {
		return te instanceof IDrawerGroup;
	}

	@Override
	public int getSlotCount(TileEntity te, int setID) {
		int slots = 0;
		IDrawerGroup group = (IDrawerGroup) te;
		for (int i = 0; i < group.getDrawerCount(); i++) {
			if (group.isDrawerEnabled(i)) {
				IDrawer drawer = group.getDrawer(i);
				if (drawer != null) {
					if (drawer.isEmpty()) {
						slots += drawer.getMaxCapacity(dummyItemOnePerStack);
					} else {
						if (drawer instanceof IFractionalDrawer && ((IFractionalDrawer)drawer).getConversionRate() != 1) {
							// If this is a compacting drawer, then only include slots for the most compact item.
							continue;
						}
						slots += (drawer.getMaxCapacity() / drawer.getStoredItemStackSize());
					}
				}
			}
		}
		return slots;
	}

	@Override
	public ItemStack[] getContents(TileEntity te, int setID) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		IDrawerGroup group = (IDrawerGroup) te;
		for (int i = 0; i < group.getDrawerCount(); i++) {
			if (group.isDrawerEnabled(i)) {
				IDrawer drawer = group.getDrawer(i);
				if (drawer != null) {
					ItemStack stack = drawer.getStoredItemCopy();
					if (stack != null)  list.add(stack);
				}
			}
		}
		return list.toArray(new ItemStack[list.size()]);
	}

}
