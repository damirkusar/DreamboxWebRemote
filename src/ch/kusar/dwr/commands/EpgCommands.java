///**
// * This class is to send end receive data to and from Dreambox. 
// * @author Damir Kusar (damir@kusar.ch)
// * @date 09.05.2011
// * @version 0.1 - Created the class 
// * 
// */
//
//package ch.kusar.dwr.commands;
//
//import java.util.ArrayList;
//
//import ch.kusar.dwr.dataobjects.Epg;
//
//public class EpgCommands extends Commands {
//
//	/**
//	 * This Method gets the current EPG over http from the DreamBox.
//	 * @return ArrayList<Epg>
//	 */
//	public ArrayList<Epg> loadCurrentEpg() {
//		connectToDreamBox(getEpgPath(), null);
//		return epgStringParser(httpGetExecuteResult());
//	}
//
//	/**
//	 * This Method gets the EPG from a specified channel over http from the
//	 * DreamBox.
//	 * @return ArrayList<Epg>
//	 */
//	public ArrayList<Epg> loadEpgFromRef(String ref) {
//		connectToDreamBox(getEpgPath(), ref);
//		return epgStringParser(httpGetExecuteResult());
//	}
//
//	/**
//	 * This Method parses the returned http string and saves it in the Epg ArrayList.
//	 * @return ArrayList<Epg>
//	 */
//	private ArrayList<Epg> epgStringParser(String htmlpage) {
//		Epg.getEpgList().clear();
//		Epg epg = null;
//		String[] middleSplit = htmlpage.split("middle");
//		String[] recordLink = null;
//		String[] description = null;
//		String[] timeDate = null;
//
//		for (int i = 1; i < middleSplit.length; i++) {
//			epg = new Epg();
//			recordLink = middleSplit[i].split("'");
//
//			timeDate = middleSplit[i].split("time");
//			timeDate = timeDate[1].split(">");
//			timeDate = timeDate[1].split("<");
//			timeDate = timeDate[0].split(" ");
//
//			description = middleSplit[i].split(">");
//			description = description[description.length - 4].split("<");
//
//			if (recordLink[7].contains("\\")) {
//				System.out.println("NOOOOOO");
//				System.out.println(recordLink[7]);
//				epg.setShow(recordLink[7].replaceAll("\\\\", "")
//						+ recordLink[8]);
//				epg.setChannel(recordLink[10]);
//				epg.setTimerendaction(recordLink[12]);
//			} else {
//				epg.setShow(recordLink[7]);
//				epg.setChannel(recordLink[9]);
//				epg.setTimerendaction(recordLink[11]);
//			}
//			epg.setRef(recordLink[1]);
//			epg.setStart(recordLink[3]);
//			epg.setDuration(recordLink[5]);
//			epg.setDescription(description[0]);
//			epg.setDate(timeDate[0]);
//			epg.setTime(timeDate[1]);
//			Epg.getEpgList().add(epg);
//		}
//		System.out.println("Start desc Damir Kusar");
//		for (int i = 0; i < Epg.getEpgList().size(); i++) {
//			System.out.println(Epg.getEpgList().get(i).toString());
//		}
//		System.out.println("Ende Damir Kusar");
//		return Epg.getEpgList();
//	}
//}