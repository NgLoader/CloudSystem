package de.ng.cloud.core.network.packet.packets;

import java.io.IOException;

import de.ng.cloud.core.ServerType;
import de.ng.cloud.core.network.packet.Packet;
import de.ng.cloud.core.network.packet.PacketSerializer;

public class PacketInitializing implements Packet {

	public ServerType type;

	public String deamonName;
	public String token;

	public PacketInitializing() { }

	public PacketInitializing(String deamonName, String token) {
		this.deamonName = deamonName;
		this.token = token;
	}

	@Override
	public void read(PacketSerializer packetSerializer) throws IOException {
		this.type = packetSerializer.readEnum(ServerType.class);

		switch (type) {
		case MASTER:
			break;

		case DEAMON:
			this.deamonName = packetSerializer.readString();
			this.token = packetSerializer.readString();
			break;

		case WEBUSER:
			break;

		case PROXY:
			break;

		case BUKKIT:
			break;
		default:
			break;
		}
	}

	@Override
	public void write(PacketSerializer packetSerializer) throws IOException {
		packetSerializer.writeEnum(type);

		switch (type) {
		case MASTER:
			break;

		case DEAMON:
			packetSerializer.writeString(deamonName);
			packetSerializer.writeString(token);
			break;

		case WEBUSER:
			break;

		case PROXY:
			break;

		case BUKKIT:
			break;
		default:
			break;
		}
	}
}