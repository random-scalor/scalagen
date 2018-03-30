/*
 * Copyright (C) 2011, Mysema Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.scalagen;

import java.io.File;

import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.LifecyclePhase;

/**
 *
 */
@Mojo( //
		name = "test", //
		defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES //
)
public class ScalagenTestMojo extends ScalagenAnyMojo {

	/**
	 * 
	 */
	@Parameter(name = "sourceFolder", property = "sourceFolder", defaultValue = "${project.basedir}/repo/src/test/java")
	File sourceDir;

	/**
	 * 
	 */
	@Parameter(name = "targetFolder", property = "targetFolder", defaultValue = "${project.basedir}/src/test/scala")
	File targetDir;

	@Override
	File sourceFolder() {
		return sourceDir;
	}

	@Override
	File targetFolder() {
		return targetDir;
	}

}
