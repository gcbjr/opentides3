/*
   Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.    
 */

package org.opentides.annotation.field;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Generates an input checkbox.
 * <b>Sample Usage:</b><br />
 * &emsp;&emsp;&emsp;<code>@Checkbox (label = "checkBoxLabel", requiredField = "true")</code><br />
 * @author allanctan
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Checkbox {

	/**
	 * Refers to the name identifier of the field; default value is <code><b>null</b></code>.
	 * 
	 */
	String label() default "";
	
	/**
	 * Refers to the SystemCodes values used to populate the list with; default value is <code><b>null</b></code>.<br>
	 * The <code>category</code> element takes precedence over the <code>options</code> element when both 
	 * are present.
	 * @see SystemCodes
	 */
	String category() default "";
	
	/**
	 * Refers to the hardcoded values used to populate the list with; default value is <code><b>null</b></code>. <br>
	 * Values are enclosed in { } and separated by commas.<br>
	 * The <code>category</code> element takes precedence over the <code>options</code> element when both 
	 * are present.<p>
	 * <b>Example:</b><br />
	 * &emsp;&emsp;&emsp;<code>@CheckBox (options = {"apple", "banana", "cherry"})</code>
	 */
	String[] options() default "";

	/**
	 * Refers to the parameters to be added; default value is <code><b>null</b></code>.
	 * 
	 */	
	String[] springParams() default "";
	
	/**
	 * Determines if the field is included as a search criteria; default value is <code><b>false</b></code>.
	 * 
	 */
	boolean isSearchable() default false;
		
	/**
	 * Determines if the field is included in the table view; default value is <code><b>false</b></code>.
	 * 
	 */
	boolean isListed() default false;
}
