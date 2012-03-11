package net.bogdoll.nb2e;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarFile;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class Platform
{
	private final Map<String,File> mModules = Maps.newHashMap();
	
	public Platform(File aNetbeansPlatformDir, Iterable<String> aClusterPaths, Iterable<String> aDisabledModules) throws IOException 
	{
		Set<String> disabledModules = Sets.newHashSet(aDisabledModules);
		
 		for(String clusters : aClusterPaths) {
			File cluster = new File(clusters);
			File modules = new File(cluster, "modules");
			for(File module : modules.listFiles()) {
				if(module.isFile() && module.getName().endsWith(".jar")) {
					String codeBaseName = getCodeBaseName(module);
					if(!disabledModules.contains(codeBaseName)) {
						mModules.put(codeBaseName, module);
					}
				}
			}
		}

 		File platformDir = new File(aNetbeansPlatformDir, "platform");
 		File[] addModules = new File[]{
 				new File(platformDir, "lib"),
 				new File(platformDir, "core"), 
 		};
 		for(File modules : addModules) {
			for(File module : modules.listFiles()) {
				if(module.isFile() && module.getName().endsWith(".jar")) {
					String codeBaseName = getCodeBaseName(module);
					if(!disabledModules.contains(codeBaseName)) {
						mModules.put(codeBaseName, module);
					}
				}
			}
 		}

	}

	private String getCodeBaseName(File aModule) throws IOException {
		JarFile jar = new JarFile(aModule);
		return jar.getManifest().getMainAttributes().getValue("OpenIDE-Module");
	}

	public void resolveModuleDependencies(Collection<Dependency> aDependencies) {
		for(Dependency dep : aDependencies) {
			File module = mModules.get(dep.getCodeNameBase());
			dep.setModule(module);
		}
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("modules", mModules)
				.toString();
	}
}