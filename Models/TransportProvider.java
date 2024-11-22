package Models;

public class TransportProvider {
	String ID;
	String Name;
	String Rating;
	String FleetSize;
	String Contact;
	String VehicleTypes;
	
	public TransportProvider(String ID,String Name,String Rating,String FleetSize,String Contact,String VehicleTypes) {
		this.ID = ID;
		this.Name = Name;
		this.Rating = Rating;
		this.FleetSize = FleetSize;
		this.Contact = Contact;
		this.VehicleTypes = VehicleTypes;
	}
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return Name;
	}
	public String getRating() {
		return Rating;
	}
	public String getFleetSize() {
		return FleetSize;
	}
	public String getContact() {
		return Contact;
	}
	public String getVehicleTypes() {
		return VehicleTypes;
	}
	
}
