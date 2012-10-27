package calico.plugins.IntegratedMapPlugin.components;

import java.awt.Image;

import javax.swing.JOptionPane;

import calico.components.CGroupImage;
import calico.inputhandlers.CalicoInputManager;
import calico.networking.netstuff.CalicoPacket;
import calico.plugins.IntegratedMapPlugin.inputhandlers.IntegratedMapInputHandler;

public class IntegratedMap extends CGroupImage 
{
	private double longitude;
	private double latitude;
	
	public IntegratedMap(long uuid, long cuid, Image image) {
		super(cuid, cuid, image);
		
		//override load signature of scrap
		
		longitude = Double.parseDouble(JOptionPane.showInputDialog("Enter longitude"));
		latitude = Double.parseDouble(JOptionPane.showInputDialog("Enter latitude"));
		JOptionPane.showMessageDialog(null, "Penis");
		networkLoadCommand = calico.plugins.IntegratedMapPlugin.IntegratedMapPluginNetworkCommands.CUSTOM_SCRAP_LOAD;
	}
	
	public IntegratedMap(long uuid, long cuid, Image image, double integratedMapLongitude, double integratedMapLatitude) {
		super(uuid, cuid, image);
		
		this.longitude = integratedMapLongitude;
		this.latitude = integratedMapLatitude;
//		JOptionPane.showMessageDialog(null, "Penis");
		//override load signature of scrap
		networkLoadCommand = calico.plugins.IntegratedMapPlugin.IntegratedMapPluginNetworkCommands.CUSTOM_SCRAP_LOAD;
		
	
	}
	
	@Override
	public void setInputHandler()
	{	
		CalicoInputManager.addCustomInputHandler(this.uuid, new IntegratedMapInputHandler(this.uuid));	
	}	
	
	/**
	 * Serialize this activity node in a packet
	 */
	@Override
	public CalicoPacket[] getUpdatePackets(long uuid, long cuid, long puid,
			int dx, int dy, boolean captureChildren) {
		
		//Creates the packet for saving this CGroup
		CalicoPacket packet = super.getUpdatePackets(uuid, cuid, puid, dx, dy,
				captureChildren)[0];

		//Appends my own informations to the end of the packet
		//Example: packet.putDouble(responseTime);

		return new CalicoPacket[] { packet };

	}
	
	@Override
	public int get_signature() {
		//Return 0 since this is client side only
		return 0;
	}
	
	
}
