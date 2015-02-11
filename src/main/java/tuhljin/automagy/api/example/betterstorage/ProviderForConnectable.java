package tuhljin.automagy.api.example.betterstorage;

import net.mcft.copy.betterstorage.tile.entity.TileEntityConnectable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tuhljin.automagy.api.inventarium.DefaultInventariumContentsProvider;

public class ProviderForConnectable extends DefaultInventariumContentsProvider {

	public static final int PRIORITY = 101;

	@Override
	public boolean canHandleTile(TileEntity te) {
		return te instanceof TileEntityConnectable;
	}

	@Override
	public int getSlotCount(TileEntity te, int setID) {
		// Ignore this if it is not the "main" container. (The main container is either not connected to other
		// containers or it is the "boss" of any containers it is connected to.)
		if (((TileEntityConnectable) te).isMain())  return super.getSlotCount(te, setID);
		return 0;
	}

	@Override
	public ItemStack[] getContents(TileEntity te, int setID) {
		return super.getContents(te, setID);
	}

}
