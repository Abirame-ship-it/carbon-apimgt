/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wso2.carbon.apimgt.rest.api.util.resolver;

import org.apache.cxf.message.Message;
import org.wso2.carbon.apimgt.rest.api.common.RestApiConstants;

public class ChoreoResolver implements OrganizationResolver {

    @Override
    public String resolve(Message message) {
        String queryString = (String) message.get(Message.QUERY_STRING);
        String organizationId = null;
        if (queryString != null) {
            String[] queries = queryString.split("&");
            if (queries != null) {
                for (int i = 0; i < queries.length; i++) {
                    if (queries[i].startsWith("organizationId")) {
                        organizationId = queries[i].split("=")[1];
                        message.put(RestApiConstants.ORGANIZATION, organizationId);
                        break;
                    }
                }
            }
        }
        return organizationId;
    }

}
