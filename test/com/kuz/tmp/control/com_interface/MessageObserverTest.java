package com.kuz.tmp.control.com_interface;

import com.kuz.tmp.model.bean.Message;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;

/**
 *
 * @author Kasun Amarasena
 */
public class MessageObserverTest {
    
//    private final DateFormat format2 = new SimpleDateFormat("yy/MM/ddHH:mm:ssX");
//
//    private String simData = "OK\n"
//            + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          \n"
//            + "\n"
//            + "OK\n"
//            + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          \n"
//            + "\n"
//            + "+CMGL: 0,\"REC READ\",\"85115971031013265108101114116\",,\"15/02/07,13:02:14-32\"\n"
//            + "Balance Bundle : 1,024.00MB\n"
//            + "Unbilled Usage as at 01/01/2015 19:01:09-Rs. 0.00,\n"
//            + "Billed Due amount - Rs.1,508.37, \n"
//            + "Total amount -Rs.1,508.37  \n"
//            + "+CMGL: 1,\"REC READ\",\"85115971031013265108101114116\",,\"15/02/19,06:23:41+22\"\n"
//            + "Balance Bundle : 1,024.00MB\n"
//            + "Unbilled Usage as at 01/01/2015 19:01:09-Rs. 0.00,\n"
//            + "Billed Due amount - Rs.1,508.37, \n"
//            + "Total amount -Rs.1,508.37  \n"
//            + "+CMGL: 2,\"REC READ\",\"+94715882182\",,\"15/02/25,22:22:53+22\"\n"
//            + "Testing 2.0\n"
//            + "\n"
//            + "OK";
//
//    public MessageObserverTest() {
//    }
//
//    @Test
//    public void testUpdate_List() {
//        List<Message> msgList = process(simData);
//        for (Message message : msgList) {
//            System.out.println(message);
//        }
//
//    }
//
//    private List<Message> process(String simData) {
//        simData = simData.trim();
//        if (simData.equals("OK")) {
//            return null;
//        }
//
//        List<Message> list = new ArrayList<>();
//        String[] lines = simData.split("\n");
//
//        Message msg = null;
//
//        for (int i = 0; i < lines.length; i++) {
//            String line = lines[i];
//
//            if (line.indexOf("+CMGL:") > -1) {
//                msg = new Message();
//
//                line = line.replaceAll("\"", "");
//                String[] info = line.split(",");
//
//                //setting number
//                msg.setNumber(info[2]);
//
//                //setting received date               
//                msg.setReceivedDate(new Date());
//
//                //setting sent date   
//                
//                try {
//                    Date receivedDate = format2.parse(info[4] + dateFormat(info[5]));
//                    System.out.println("----------------------------------------");
//                    System.out.println(dateFormat(info[5]));
//                    System.out.println("----------------------------------------");
//                    
//                    msg.setSentDate(receivedDate);
//                } catch (ParseException ex) {
//                }
//
//                //setting message contents
//                int temp = i;
//                while ((++temp < lines.length) && lines[temp].indexOf("+CMGL:") <= -1) {
//                    if (lines[temp].indexOf("OK") > -1) {
//                        continue;
//                    }
//                    if (msg.getContents() != null) {
//                        msg.setContents(msg.getContents() + lines[temp] + "\n");
//                    } else {
//                        msg.setContents(lines[temp] + "\n");
//                    }
//                }
//                i = temp - 1;
//            }
//            if (msg != null) {
//                list.add(msg);
//            }
//        }
//        return list;
//    }
//
    private String format(String str) {        
        Double t = (Double.valueOf(str.substring(9)) / 4)*100;        
        String temp = String.valueOf(t.intValue());
        if(t<1000){
            temp = "0"+temp;
        }       
        return str.substring(0, 9) + temp;
    }
    
    @Test
    public void test() throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ssZ");
        
        String str ="13:02:14-32";
        str = format(str);
        System.out.println(str);
        Date parse = dateFormat.parse(str);
        System.out.println(dateFormat.format(parse));
    }

}
