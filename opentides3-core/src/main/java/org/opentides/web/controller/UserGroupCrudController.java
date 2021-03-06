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
package org.opentides.web.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opentides.bean.user.UserAuthority;
import org.opentides.bean.user.UserGroup;
import org.opentides.service.UserGroupService;
import org.opentides.web.validator.UserGroupValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This is the controller class for {@link UserGroup}. It extends the
 * {@link BaseCrudController} class. Mapped to {@code /organization/usergroups}.
 * Scaffold generated by opentides3 on Jan 16, 2013 12:40:25. 
 * @author opentides
 */
@Controller 
@RequestMapping("/organization/usergroups")
public class UserGroupCrudController extends BaseCrudController<UserGroup> {
	
	@Autowired
	private UserGroupValidator userGroupValidator;
	
	@InitBinder("formCommand")
    protected void transactionInitBinder(WebDataBinder binder) {
        binder.setValidator(userGroupValidator);
    }

	/**
	 * Post construct that initializes the crud page to {@code "/base/system-codes-crud"}.
	 */
	@PostConstruct
	public void init() {
		singlePage = "/base/usergroup-crud";
		// no pagination support
		pageSize = 0;
	}

	/* (non-Javadoc)
	 * @see org.opentides.web.controller.BaseCrudController#preUpdate(org.opentides.bean.BaseEntity, org.springframework.validation.BindingResult, org.springframework.ui.Model, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/**
	 * Method that deletes all the user authorities in the {@code removeList} of {@link UserGroup} from the
	 * database.
	 * 
	 * @param command
	 * @param bindingResult
	 * @param uiModel
	 * @param request
	 * @param response
	 */
	@Override
	protected void preUpdate(UserGroup command, BindingResult bindingResult,
			Model uiModel, HttpServletRequest request,
			HttpServletResponse response) {
		for (UserAuthority deleteRole : command.getRemoveList()) {
			((UserGroupService) getService()).removeUserAuthority(deleteRole);			
		}
	}

	/* (non-Javadoc)
	 * @see org.opentides.web.controller.BaseCrudController#onLoadSearch(org.opentides.bean.BaseEntity, org.springframework.validation.BindingResult, org.springframework.ui.Model, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	/**
	 * Method that adds to the model parameter {@code uiModel} the results {@code results} 
	 * of {@link BaseCrudController } search method.
	 */
	@Override
	protected void onLoadSearch(UserGroup command, BindingResult bindingResult, 
			Model uiModel, HttpServletRequest request,
			HttpServletResponse response) {
		uiModel.addAttribute("results", search(command, request));
	}

	/* (non-Javadoc)
	 * @see org.opentides.web.controller.BaseCrudController#postCreate(org.opentides.bean.BaseEntity)
	 */
	@Override
	protected void postCreate(UserGroup command) {
		if(command.getIsDefault() != null && command.getIsDefault())
			((UserGroupService) getService()).removeOldDefaultUserGroup(command.getId());
	}

	/* (non-Javadoc)
	 * @see org.opentides.web.controller.BaseCrudController#postUpdate(org.opentides.bean.BaseEntity)
	 */
	@Override
	protected void postUpdate(UserGroup command) {
		if(command.getIsDefault() != null && command.getIsDefault())
			((UserGroupService) getService()).removeOldDefaultUserGroup(command.getId());
	}

	/**
	 * Returns a map of all the authorities defined in application context.
	 * 
	 * @return a map containing all the authorities
	 */
	@ModelAttribute("authoritiesList")
	public Map<String, String> getAuthoritiesList() {
		return ((UserGroupService)service).getAuthorities();
	}


}
