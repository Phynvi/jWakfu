package com.velocity.jwakfu.net.packets.in;

import java.util.Random;

import io.netty.buffer.ByteBuf;

import com.velocity.jwakfu.model.Appearance;
import com.velocity.jwakfu.model.Breed;
import com.velocity.jwakfu.model.GameCharacter;
import com.velocity.jwakfu.model.Nation;
import com.velocity.jwakfu.net.packets.IncomingPacket;
import com.velocity.jwakfu.net.packets.out.Packet2077SendCompanions;
import com.velocity.jwakfu.net.packets.out.Packet2054CharCreationResponse;
import com.velocity.jwakfu.session.ClientSession;
import com.velocity.jwakfu.util.DataUtils;

public class Packet2053CreateCharacter implements IncomingPacket {
	
	private static final Random r = new Random();

	@SuppressWarnings("unused")
	@Override
	public void decode(ClientSession session, ByteBuf buffer, int size) {
		//int dataSize = buffer.readShort();
		//int infoType = buffer.readByte(); //always 1
		
		//int idType = buffer.readByte();
		long owner = buffer.readLong();
		Appearance appearance = new Appearance(buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readByte(), buffer.readShort());

		String name = DataUtils.readString(buffer);
		//Breed breed = Breed.values()[buffer.readUnsignedShort() - 1];

		//boolean hasCreationData = buffer.readBoolean();
		//if (hasCreationData)
			buffer.readByte();
		
		GameCharacter charac = new GameCharacter(name, r.nextInt(), 0, owner, Breed.CRA, 0, Nation.NONE, appearance);
		//session.getPlayer().addCharacter(charac);
		//session.getPlayer().save();
		
		session.write(new Packet2054CharCreationResponse());
		session.write(new Packet2077SendCompanions(session.getPlayer()));
	}

}
