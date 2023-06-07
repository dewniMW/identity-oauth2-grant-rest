/*
 *  Copyright (c) 2023, WSO2 LLC (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 LLC licenses this file to you under the Apache license,
 *  Version 2.0 (the "license"); you may not use this file except
 *  in compliance with the license.
 *  You may obtain a copy of the license at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

package org.wso2.carbon.identity.oauth2.grant.mfa.framework.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.oauth2.grant.mfa.framework.constant.Constants;
import org.wso2.carbon.identity.oauth2.grant.mfa.framework.context.AuthRestAuthenticationContext;
import org.wso2.carbon.identity.oauth2.grant.mfa.framework.exception.AuthenticationException;
import org.wso2.carbon.identity.oauth2.grant.mfa.framework.util.Util;
import org.wso2.carbon.identity.recovery.IdentityRecoveryException;
import org.wso2.carbon.identity.recovery.util.Utils;

public class AccountLockListener extends AbstractAuthenticationListener {

	private static final Log LOG = LogFactory.getLog(AccountLockListener.class);

	@Override
	public int getExecutionOrderId() {
		return 13;
	}

	@Override
	public boolean doPreAuthenticate(AuthRestAuthenticationContext authContext) throws AuthenticationException {
		return true;
	}

	@Override
	public boolean doPostAuthenticate(AuthRestAuthenticationContext authContext) throws AuthenticationException {
		// check whether user account is locked
		try {
			if (Utils.isAccountLocked(authContext.getUser())) {
				throw Util.handleClientException(Constants.ErrorMessage.CLIENT_LOCKED_ACCOUNT, authContext.getUser()
						.toFullQualifiedUsername());
			}
		} catch (IdentityRecoveryException e) {
			throw Util.handleServerException(Constants.ErrorMessage.SERVER_ACCOUNT_STATUS_ERROR,
					String.format("Error while checking the account status for the user : %s.",
							authContext.getUser().toFullQualifiedUsername()), e);
		}
		return true;
	}
}
