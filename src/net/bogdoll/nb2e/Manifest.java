package net.bogdoll.nb2e;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class Manifest {
	private Map<String,String> mArgs = Maps.newHashMap();

	public void parse(InputStream aInput) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(aInput));
		while(true) {
			String line = reader.readLine();
			if(line==null) break;
			if(line.isEmpty()) continue;
			
			int pos = line.indexOf(':');
			if(pos<0) {
				System.err.println("Line '"+line+"' is invalid, because it contains no ':'; skipping this line");
				continue;
			}
			String key = line.substring(0, pos).trim();
			String value = "";
			String valueCandidate = line.substring(pos+1).trim();
			while(valueCandidate.endsWith("\\")) {
				value += valueCandidate.substring(0, valueCandidate.length()-1).trim();
				valueCandidate = reader.readLine();
				if(valueCandidate==null) {
					System.err.println("Expected value continuation on this line, but found EOL");
					break;
				}
			}
			value += valueCandidate.substring(0, valueCandidate.length()).trim();
			
			System.out.println(key+" -> "+value);
			mArgs.put(key,  value);
		}
	}
	
	public String getValue(String aName) {
		return mArgs.get(aName);
	}
	
	public Collection<String> getKeys() {
		return mArgs.keySet();
	}
}
