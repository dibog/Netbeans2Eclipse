package net.bogdoll.nb2e;

import java.io.File;
import java.io.IOException;

import org.jdom.Element;
import org.jdom.Namespace;

import com.google.common.base.Objects;

public class Dependency {

	private String mCodeNameBase;
	private File mModule;

	public Dependency(Element aDependency, Namespace aModuleProjectNs) {
		mCodeNameBase = aDependency.getChild("code-name-base", aModuleProjectNs).getTextTrim();
	}
	
	public Dependency(String aCodeNameBase, File aModule) {
		mModule = aModule;
		mCodeNameBase = aCodeNameBase;
	}
	
	public String getCodeNameBase() {
		return mCodeNameBase;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("code-name-base", mCodeNameBase)
				.add("module", mModule)
				.toString();
	}

	public void setModule(File aModule) {
		mModule = aModule;
	}
	
	public File getLocation() throws IOException {
		return mModule.getCanonicalFile();
	}
}