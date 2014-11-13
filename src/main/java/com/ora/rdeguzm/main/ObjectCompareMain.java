package com.ora.rdeguzm.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import difflib.ChangeDelta;
import difflib.DeleteDelta;
import difflib.Delta;
import difflib.DiffUtils;
import difflib.InsertDelta;
import difflib.Patch;

public class ObjectCompareMain {

	// Helper method for get the file content
	private static List<String> fileToLines(String filename) {
		List<String> lines = new LinkedList<String>();
		String line = "";
		try {
			// File file = new
			// File(ObjectCompareMain.class.getResource(filename).getFile());
			BufferedReader in = new BufferedReader(new FileReader(filename));
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public void generateLineByLineCount(StringBuilder htmlBuilder,List<String> original, List<String> revised, int i){
		if(original.size() > i) {
			generateLineByLine(htmlBuilder,original,revised,i);
		}else if(original.size() < i && revised.size() > i) {
			
			if(revised.get(i).equals("")) {
				htmlBuilder.append("<td>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td> ");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
			}else {
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
			}
		}else if(original.size() > i && revised.size() < i) {
			htmlBuilder.append("<td style='background-color:red;'>");
			htmlBuilder.append(original.get(i));
			htmlBuilder.append("</td>");
			htmlBuilder.append("<td style='background-color:red;'>");
			htmlBuilder.append("");
			htmlBuilder.append("</td>");
			
			if(original.get(i).equals("")) {
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
			}else {
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
			}
		}else if(revised.size() < i && original.size() > i) {
			
			if(revised.get(i).equals("")) {
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td> ");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
			}else {
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
			}
		}else if(revised.size() > i && original.size() < i) {
			htmlBuilder.append("<td style='background-color:palegreen;'>");
			htmlBuilder.append("");
			htmlBuilder.append("</td>");
			htmlBuilder.append("<td style='background-color:palegreen;'>");
			htmlBuilder.append(revised.get(i));
			htmlBuilder.append("</td>");
			
			if(revised.get(i).equals("")) {
				htmlBuilder.append("<td>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
			}else {
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append("");
				htmlBuilder.append("</td>");
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
			}
		}
	}
	
	public void generateLineByLine(StringBuilder htmlBuilder,List<String> original, List<String> revised, int i) {
		//	If they are different.
		if(original.size() > revised.size()) {
			if(original.get(i).equals(revised.get(i))) {
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(original.get(i).trim().equals("") && !revised.get(i).trim().equals("")) {//	if the revised has a new addition to the original.
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(!original.get(i).trim().equals("") && !revised.get(i).trim().equals("")) {//	if the original was removed and replaced
				
				htmlBuilder.append("<td style='background-color:lightyellow;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:lightyellow;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
				
			}else if(original.get(i).trim().equals("")) {	//	if the original was removed with no replacement.
	
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(revised.get(i).equals(original.get(i))) {
				
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(revised.get(i).trim().equals("") && !original.get(i).trim().equals("")) {//	if the revised has a new addition to the original.
				
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(!revised.get(i).trim().equals("") && !original.get(i).trim().equals("")) {//	if the original was removed and replaced
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(revised.get(i).trim().equals("")) {	//	if the original was removed with no replacement.
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}
			
		}else if(revised.size() > original.size()) {
			if(revised.get(i).equals(original.get(i))) {
				
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(revised.get(i).trim().equals("") && !original.get(i).trim().equals("")) {//	if the revised has a new addition to the original.
				
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:red;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(!revised.get(i).trim().equals("") && !original.get(i).trim().equals("")) {//	if the original was removed and replaced
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(revised.get(i).trim().equals("")) {	//	if the original was removed with no replacement.
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(original.get(i).equals(revised.get(i))) {
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(original.get(i).trim().equals("") && !revised.get(i).trim().equals("")) {//	if the revised has a new addition to the original.
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:palegreen;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}else if(!original.get(i).trim().equals("") && !revised.get(i).trim().equals("")) {//	if the original was removed and replaced
				
				htmlBuilder.append("<td style='background-color:lightyellow;'>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td style='background-color:lightyellow;'>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
				
			}else if(original.get(i).trim().equals("")) {	//	if the original was removed with no replacement.
	
				htmlBuilder.append("<td>");
				htmlBuilder.append(original.get(i));
				htmlBuilder.append("</td>");
				
				htmlBuilder.append("<td>");
				htmlBuilder.append(revised.get(i));
				htmlBuilder.append("</td>");
				
			}
			
		}
		
	}
	
	public void newLineByLineLogic() {
		List<String> original = fileToLines("/Users/alvinreyes/Documents/DevWorkspace/object-compare/src/main/resources/originalFile.txt");
		List<String> revised = fileToLines("/Users/alvinreyes/Documents/DevWorkspace/object-compare/src/main/resources/revisedFile.txt");

		Patch patch = DiffUtils.diff(original, revised);
		List<Delta> deltas = patch.getDeltas();
		
		String bgColor = "white";
		
		//for (Delta delta : deltas) {
			
			//if(delta instanceof InsertDelta) {
			//	bgColor = "palegreen";
			//}else if(delta instanceof DeleteDelta) {
			//	bgColor = "red";
			//}else if(delta instanceof ChangeDelta) {
			//	bgColor = "lightyellow";
			//}
			
			// get the count.
			int count = (original.size() >= revised.size() ? original.size() : revised.size());
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<table>");
			for(int i=0;i<count;i++) {
				
				htmlBuilder.append("<tr>");
				htmlBuilder.append("<td>" + (i + 1) + "</td>");
				
				try {
					generateLineByLine(htmlBuilder,original,revised,i);
				}catch(Exception e) {
					try {
						generateLineByLineCount(htmlBuilder,original,revised,i);
					}catch(Exception exorig) {
						
					}
					
					
				}
				
				htmlBuilder.append("</tr>");
				//	if the original was removed
			}
			
			System.out.println(htmlBuilder.toString());
			
//			
//			//	Get the original and revision lines.
//			for(Object origObject:delta.getOriginal().getLines()) {
//				System.out.println(origObject.toString());
//			}
//			
//			for(Object revObject:delta.getRevised().getLines()) {
//				System.out.println(revObject.toString());
//			}
			
		//}
	}
	
	public void origLineByLineLogic() {

		List<String> original = fileToLines("/Users/alvinreyes/Documents/DevWorkspace/object-compare/src/main/resources/originalFile.txt");
		List<String> revised = fileToLines("/Users/alvinreyes/Documents/DevWorkspace/object-compare/src/main/resources/revisedFile.txt");

		Patch patch = DiffUtils.diff(original, revised);

		// for (Delta delta : patch.getDeltas()) {
		// System.out.println(delta);
		// }

		String[] cors = new String[] { "palegreen", "khaki", "pink",
				"moccasin", "lightskyblue", "lightyellow", "coral",
				"aliceblue", "yellowgreen", "beige", "lightpink" };
		StringBuilder tl = new StringBuilder();
		StringBuilder tr = new StringBuilder();
		StringBuilder tline = new StringBuilder();
		// Patch patch = DiffUtils.diff(original, revised);
		//Patch patch = DiffUtils
		//		.parseUnifiedDiff(fileToLines("/Users/alvinreyes/Documents/DevWorkspace/object-compare/src/main/resources/originalFile.txt"));
		List<Delta> deltas = patch.getDeltas();
		L2B l2B = new L2B();
		String bgColor = "white";
		int cori = 0;
		int last = 0;
		for (Delta delta : deltas) {
			
			
			if(delta instanceof InsertDelta) {
				bgColor = "palegreen";
			}else if(delta instanceof DeleteDelta) {
				bgColor = "red";
			}else if(delta instanceof ChangeDelta) {
				bgColor = "lightyellow";
			}
			
			if (last + 1 < delta.getOriginal().getPosition()) { 
				tl.append("<pre style=''>\n");
				tr.append("<pre style=''>\n");
				for (int i = last + 1; i < delta.getOriginal().getPosition(); i++) {
					tl.append(original.get(i) + "\n");
					tr.append(original.get(i) + "\n");
				}
				tl.append("</pre>\n");
				tr.append("</pre>\n");
			}
			List<?> or = delta.getOriginal().getLines();
			
			//System.out.println("<pre style='background-color:" + cors[cori] + ";'>\n" + l2B.l2b(or) + "\n</pre>");
			tl.append("<pre style='background-color:" + bgColor + ";'>\n" + l2B.l2b(or) + "\n</pre>");
			
			List<?> re = delta.getRevised().getLines();
			//System.out.println("<pre style='background-color:" + cors[cori] + ";'>\n" + l2B.l2b(re) + "\n</pre>");
			tr.append("<pre style='background-color:" + bgColor + ";'>\n" + l2B.l2b(re) + "\n</pre>");
			
			cori = (cori < cors.length) ? cori + 1 : 0;
			last = delta.getOriginal().last();

		}
		if (last + 1 < original.size()) { // last is not delta
			
			tl.append("<pre style=''>\n");
			tr.append("<pre style=''>\n");
			for (int i = last + 1; i < original.size(); i++) {
				tl.append(original.get(i) + "\n");
				tr.append(original.get(i) + "\n");
			}
			tl.append("</pre>\n");
			tr.append("</pre>\n");
		}

		System.out.println("<html><table><tr><td style='vertical-align:top;'>"
				+ tline.toString() + "</td><td style='vertical-align:top;'>" 
				+ tl.toString() + "</td><td style='vertical-align:top;'>"
				+ tr.toString() + "</td></tr></table></html>");
	
	}
	

	public static void main(String[] args) {
		new ObjectCompareMain().origLineByLineLogic();
		
		
	}
}

class L2B {
	private StringBuilder sb = new StringBuilder();

	StringBuilder l2b(List<?> l) {
		sb.delete(0, sb.length());
		for (Object object : l) {
			sb.append(object + "\n");
		}
		if (sb.length() >= 1)
			sb.deleteCharAt(sb.length() - 1); // last "\n"
		return sb;
	}
}
