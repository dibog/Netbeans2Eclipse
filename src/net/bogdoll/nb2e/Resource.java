package net.bogdoll.nb2e;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Objects;

public class Resource 
{
	public enum Type { UNUSED0, FILE, FOLDER, LIB }

	private final String mName;
	private final Type mType;
	private final File mLocation;
	
	public Resource(String aName, Type aType, File aLocation) {
		mName = checkNotNull(aName);
		mType = checkNotNull(aType);
		mLocation = checkNotNull(aLocation);
	}
	
	public int getType() {
		return mType.ordinal();
	}
	
	public String getName() {
		return mName;
	}
	
	public File getLocation() throws IOException {
		return mLocation.getCanonicalFile();
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("name", mName)
				.add("type", mType)
				.add("location", mLocation)
				.toString();
	}
}