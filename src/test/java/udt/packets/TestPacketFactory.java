package udt.packets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import udt.UDTPacket;
import udt.util.SequenceNumber;

public class TestPacketFactory {

	@Test
	public void testData()throws IOException{
		String test="sdjfsdjfldskjflds";

		byte[]data=test.getBytes();
		data[0]=(byte)(data[0] & 0x7f);
		UDTPacket p=PacketFactory.createPacket(data);
		DataPacket recv=(DataPacket)p;
		String t=new String(recv.getEncoded());
		assertTrue(p instanceof DataPacket);
		assertEquals(test,t);
	}

	@Test
	public void testConnectionHandshake()throws IOException{
		ConnectionHandshake p1 = new ConnectionHandshake();
		p1.setMessageNumber(9876);
		p1.setTimeStamp(3456);
		p1.setDestinationID(1);

		p1.setConnectionType(1);
		p1.setSocketType(1);
		p1.setInitialSeqNo(321);
		p1.setPacketSize(128);
		p1.setMaxFlowWndSize(128);
		p1.setSocketID(1);
		p1.setUdtVersion(4);
		p1.setAddress(InetAddress.getLocalHost());
		p1.setCookie(SequenceNumber.random());
		
		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		ConnectionHandshake p2=(ConnectionHandshake)p;
		assertEquals(p1,p2);		

	}

	@Test
	public void testAcknowledgement()throws IOException{
		Acknowledgement p1 = new Acknowledgement();
		p1.setAckSequenceNumber(1234);
		p1.setMessageNumber(9876);
		p1.setTimeStamp(3456);
		p1.setDestinationID(1);
		p1.setBufferSize(128);
		p1.setEstimatedLinkCapacity(16);
		p1.setAckNumber(9870);
		p1.setPacketReceiveRate(1000);
		p1.setRoundTripTime(1000);
		p1.setRoundTripTimeVar(500);

		byte[]p1_data=p1.getEncoded();
		UDTPacket p=PacketFactory.createPacket(p1_data);
		Acknowledgement p2=(Acknowledgement)p;
		assertEquals(p1,p2);
	}

	@Test
	public void testAcknowledgementOfAcknowledgement()throws IOException{
		Acknowledgment2 p1 = new Acknowledgment2();
		p1.setAckSequenceNumber(1230);
		p1.setMessageNumber(9871);
		p1.setTimeStamp(3451);
		p1.setDestinationID(1);

		byte[]p1_data=p1.getEncoded();
		UDTPacket p=PacketFactory.createPacket(p1_data);
		Acknowledgment2 p2=(Acknowledgment2)p;
		assertEquals(p1,p2);


	}

	@Test
	public void testNegativeAcknowledgement()throws IOException{
		NegativeAcknowledgement p1 = new NegativeAcknowledgement();
		p1.setMessageNumber(9872);
		p1.setTimeStamp(3452);
		p1.setDestinationID(2);
		p1.addLossInfo(5);
		p1.addLossInfo(6);
		p1.addLossInfo(7, 10);
		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		NegativeAcknowledgement p2=(NegativeAcknowledgement)p;
		assertEquals(p1,p2);

		assertEquals((Integer)5, (Integer)p2.getDecodedLossInfo().get(0));
		assertEquals(6, p2.getDecodedLossInfo().size());
	}

	@Test
	public void testNegativeAcknowledgement2()throws IOException{
		NegativeAcknowledgement p1 = new NegativeAcknowledgement();
		p1.setMessageNumber(9872);
		p1.setTimeStamp(3452);
		p1.setDestinationID(2);
		List<Long>loss=new ArrayList<Long>();
		loss.add(5l);
		loss.add(6l);
		loss.add(7l);
		loss.add(8l);
		loss.add(9l);
		loss.add(11l);

		p1.addLossInfo(loss);
		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		NegativeAcknowledgement p2=(NegativeAcknowledgement)p;
		assertEquals(p1,p2);

		assertEquals((Integer)5, (Integer)p2.getDecodedLossInfo().get(0));
		assertEquals(6, p2.getDecodedLossInfo().size());
	}

	@Test
	public void testNegativeAcknowledgement3()throws IOException{
		NegativeAcknowledgement p1 = new NegativeAcknowledgement();
		p1.setMessageNumber(9872);
		p1.setTimeStamp(3452);
		p1.setDestinationID(2);
		p1.addLossInfo(5);
		p1.addLossInfo(6);
		p1.addLossInfo(147, 226);
		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		NegativeAcknowledgement p2=(NegativeAcknowledgement)p;
		assertEquals(p1,p2);


	}

	@Test
	public void testShutdown()throws IOException{
		Shutdown p1 = new Shutdown();
		p1.setMessageNumber(9874);
		p1.setTimeStamp(3453);
		p1.setDestinationID(3);

		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		Shutdown p2=(Shutdown)p;
		assertEquals(p1,p2);
	}


	@Test
	public void testMessageDropRequest()throws Exception{
		MessageDropRequest p1=new MessageDropRequest();
		p1.setMessageNumber(9876);
		p1.setTimeStamp(3456);
		p1.setDestinationID(4);

		p1.setMsgFirstSeqNo(2);
		p1.setMsgLastSeqNo(3);


		byte[]p1_data=p1.getEncoded();

		UDTPacket p=PacketFactory.createPacket(p1_data);
		assertTrue(p instanceof MessageDropRequest);
		MessageDropRequest p2=(MessageDropRequest)p;
		assertEquals(p1,p2);		
	}
	
	@Test
	public void testPacketUtil()throws Exception{
		InetAddress i=InetAddress.getLocalHost();
		byte[]enc=PacketUtil.encode(i);
		PacketUtil.print(enc);
		InetAddress i2=PacketUtil.decodeInetAddress(enc, 0, false);
		System.out.println(i2);
		assertEquals(i, i2);
	}

}
