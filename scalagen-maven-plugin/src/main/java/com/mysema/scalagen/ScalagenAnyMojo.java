package com.mysema.scalagen;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * 
 */
abstract class ScalagenAnyMojo extends AbstractMojo {

	abstract File sourceFolder();

	abstract File targetFolder();

	/**
	 * 
	 */
	@Parameter(name = "project", property = "${project}", readonly = true, required = true)
	MavenProject project;

	/**
	 * 
	 */
	@Override
	public void execute() throws MojoFailureException {
		try {
			sourceFolder().mkdirs();
			targetFolder().mkdirs();
			Converter.instance().convert(sourceFolder(), targetFolder());
		} catch (Throwable e) {
			throw new MojoFailureException("", e);
		}

	}

}
