/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.shield.action.admin.role;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;

import java.io.IOException;

import static org.elasticsearch.action.ValidateActions.addValidationError;

/**
 * Request to retrieve roles from the shield index
 */
public class GetRolesRequest extends ActionRequest<GetRolesRequest> {

    private String[] roles;

    public GetRolesRequest() {
        roles = Strings.EMPTY_ARRAY;
    }

    @Override
    public ActionRequestValidationException validate() {
        ActionRequestValidationException validationException = null;
        if (roles == null) {
            validationException = addValidationError("role is missing", validationException);
        }
        return validationException;
    }

    public void roles(String... roles) {
        this.roles = roles;
    }

    public String[] roles() {
        return roles;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        roles = in.readStringArray();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeStringArray(roles);
    }
}
