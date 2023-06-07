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

package org.wso2.carbon.identity.oauth2.grant.mfa.endpoints.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;

public class AuthenticationValidationRequest {

    private String flowId;
    private String authenticator;

    /**
     * A refreshed Flow Id that can be used to uniquely identify the transaction.
     **/
    public AuthenticationValidationRequest flowId(String flowId) {

        this.flowId = flowId;
        return this;
    }

    @ApiModelProperty(value = "A refreshed Flow Id that can be used to uniquely identify the transaction")
    @JsonProperty("flowId")
    @Valid
    public String getFlowId() {
        return flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * Authenticator.
     **/
    public AuthenticationValidationRequest authenticator(String authenticator) {

        this.authenticator = authenticator;
        return this;
    }

    @ApiModelProperty(example = "SMSOTP", value = "Authenticator")
    @JsonProperty("authenticator")
    @Valid
    public String getAuthenticator() {
        return authenticator;
    }
    public void setAuthenticator(String authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthenticationValidationRequest authValidationRequest = (AuthenticationValidationRequest) o;
        return Objects.equals(this.flowId, authValidationRequest.flowId) &&
                Objects.equals(this.authenticator, authValidationRequest.authenticator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flowId, authenticator);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class AuthenticationValidationRequest {\n");

        sb.append("    flowId: ").append(toIndentedString(flowId)).append("\n");
        sb.append("    authenticator: ").append(toIndentedString(authenticator)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n");
    }
}
