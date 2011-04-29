package maintest;

import intelimed.intermediate.core.IIntelimedIntermediateCore;
import intelimed.intermediate.core.IntelimedIntermediateCore;
import intelimed.intermediate.core.exception.BusinessException;
import intelimed.intermediate.core.exception.SystemException;
import intelimed.intermediate.core.model.entity.User;

import java.util.List;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;

public class HelloWorld {
	
	public String getMessage(){
		//return "ALO JULIO";
		IIntelimedIntermediateCore iic = new IntelimedIntermediateCore();
		try {
			User u1 = new User();
			// u1.setId(new Long(2));
			u1.setName("CHIMBINHA");
			u1.setCpf("alo alo");
			u1.setPassword("uhterere");

			// iic.insert(User.class, u1);

			List<User> users = (List<User>) iic.findAll(User.class);
			for (User u : users) {
				System.out.println(u.getId() + " " + u.getName() + " "
						+ u.getCpf());
			}

			XStream xstream = new XStream();
			xstream.alias("intelimed", Vector.class);
			xstream.alias("user", User.class);

			Vector<Object> v = new Vector<Object>();

			User u2 = (User) iic.authUser(u1);
			if (u2 != null) {
				// System.out.println(u2.getName() + " = " + u1.getName());
				v.add("Ok");
				v.add(u2);
				String xml = xstream.toXML(v);
				System.out.println(xml);
				return xml;
			} else {
				v.add("Invalid");
				String xml = xstream.toXML(v);
				System.out.println(xml);
				return xml;
			}

		} catch (SystemException e) {
			System.out.println("PAU EXCEPTION1!");
			e.printStackTrace();
		} catch (BusinessException e) {
			System.out.println("PAU EXCEPTION2!");
			e.printStackTrace();
		}
		
		return "ih! fudeu! num tem colomy nem papel de pao!";
	}

	public static void main(String[] args) {
		System.out.println("Hello!");
	}
}
