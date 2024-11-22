package Models;

public class TransportProvider {
	String Name;
	String Rating;
	String FleetSize;
	String Contact;
	String VehicleTypes;
	
	public TransportProvider(String Name,String Rating,String FleetSize,String Contact,String VehicleTypes) {
		this.Name = Name;
		this.Rating = Rating;
		this.FleetSize = FleetSize;
		this.Contact = Contact;
		this.VehicleTypes = VehicleTypes;
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
