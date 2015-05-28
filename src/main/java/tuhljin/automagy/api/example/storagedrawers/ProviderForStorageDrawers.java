package tuhljin.automagy.api.example.storagedrawers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tuhljin.automagy.api.inventarium.IInventariumContentsProvider;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup;

public class ProviderForStorageDrawers implements IInventariumContentsProvider {

	public static final int PRIORITY = 120;

	ItemStack dummyItemOnePerStack = new ItemStack(Items.wooden_sword);

	@Override
	public boolean canHandleTile(TileEntity te) {
		return te instanceof IDrawerGroup;
	}

	@Override
	public int getSlotCount(TileEntity te, int setID) {
		int slots = 0;
		IDrawerGroup group = (IDrawerGroup) te;
		for (int i = 0; i < group.getDrawerCount(); i++) {
			IDrawer drawer = group.getDrawer(i);
			if (drawer != null)  slots += drawer.getMaxCapacity(dummyItemOnePerStack);
			//ItemStack stack = drawer.getStoredItemPrototype();
		}
		return slots;
	}

	@Override
	public ItemStack[] getContents(TileEntity te, int setID) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		IDrawerGroup group = (IDrawerGroup) te;
		for (int i = 0; i < group.getDrawerCount(); i++) {
			IDrawer drawer = group.getDrawer(i);
			if (drawer != null) {
				ItemStack stack = drawer.getStoredItemCopy();
				if (stack != null)  list.add(stack);
			}
		}
		return list.toArray(new ItemStack[list.size()]);
	}

}
