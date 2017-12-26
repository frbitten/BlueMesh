package bluemesh.test;


import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class TestBlueMesh {
	
	public static void main(String[] args) {
		System.out.println("Iniciando Testes...");
		
		try {
//			if(!LocalDevice.isPowerOn()) {
//				System.out.println("Device desligado.");
//				System.exit(0);
//			}
			
			LocalDevice device= LocalDevice.getLocalDevice();
			System.out.println(device.getFriendlyName()+ " - "+ device.getBluetoothAddress());
			device.setDiscoverable(DiscoveryAgent.GIAC);
			DiscoveryAgent agent= device.getDiscoveryAgent();
			
			System.out.println("Buscando dispositivos...");
			agent.startInquiry(DiscoveryAgent.GIAC,new DiscoveryListener() {
				
				@Override
				public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
					
				}
				
				@Override
				public void serviceSearchCompleted(int arg0, int arg1) {
					
				}
				
				@Override
				public void inquiryCompleted(int arg0) {
					synchronized (agent) {
                        agent.notify();
                    }
				}
				
				@Override
				public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass arg1) {
					try {
						System.out.println(remoteDevice.getFriendlyName(true)+ " - "+ remoteDevice.getBluetoothAddress());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
			synchronized (agent) {
	            agent.wait();
	            System.out.println("Completed device discovery.");
	            //teste
	        }

			
		} catch (BluetoothStateException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
