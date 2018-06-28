/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.extension.siddhi.execution.string;

import org.wso2.siddhi.annotation.Example;
import org.wso2.siddhi.annotation.Extension;
import org.wso2.siddhi.annotation.Parameter;
import org.wso2.siddhi.annotation.ReturnAttribute;
import org.wso2.siddhi.annotation.util.DataType;
import org.wso2.siddhi.core.config.SiddhiAppContext;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.core.util.config.ConfigReader;
import org.wso2.siddhi.query.api.definition.Attribute;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * replaceAll(string, regex, replacement)
 * Replaces each substring of this string that matches the given expression with the given replacement.
 * Accept Type(s): (STRING,STRING,STRING)
 * Return Type(s): STRING
 */

@Extension(
        name = "selectAndReplace",
        namespace = "str",
        description = "Replaces each substring of this string that matches the given expression " +
                "with the given replacement.",
        parameters = {
                @Parameter(name = "input.string",
                        description = "The input string to be replaced.",
                        type = {DataType.STRING}),
                @Parameter(name = "regex",
                        description = "The regular expression to be matched with the input string.",
                        type = {DataType.STRING}),
                /*@Parameter(name = "replacement.string",
                        description = "The striing with which each substring that matches the given expression should" +
                                " be replaced.",
                        type = {DataType.STRING})*/
        },
        returnAttributes = @ReturnAttribute(
                description = "This replaces each substring of the given string (i.e. str) that matches the given " +
                        "regular expression (i.e. regex) with the string specified as the replacement " +
                        "(i.e. replacement).", type = {DataType.STRING}),
        examples = @Example(description = "This returns a string after replacing the substrings of the input string" +
                " with the replacement string. In this scenario, the output is \"test hi test\" .",
                syntax = "replaceAll(\"hello hi hello\",  'hello', 'test')")
)
public class SelectAndReplaceFunctionExtension extends FunctionExecutor {

    Attribute.Type returnType = Attribute.Type.STRING;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ConfigReader configReader,
                        SiddhiAppContext siddhiAppContext) {
//        if (attributeExpressionExecutors.length != 3) {
//            throw new SiddhiAppValidationException("Invalid no of arguments passed to str:replaceAll() function, " +
//                    "required 3, but found " + attributeExpressionExecutors.length);
//        }
//        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
//            throw new SiddhiAppValidationException("Invalid parameter type found for the first argument of " +
//                    "str:replaceAll() function, " + "required " + Attribute.Type.STRING + ", but found " +
//                    attributeExpressionExecutors[0].getReturnType().toString());
//        }
//        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.STRING) {
//            throw new SiddhiAppValidationException("Invalid parameter type found for the second argument of " +
//                    "str:replaceAll() function, " + "required " + Attribute.Type.STRING + ", but found " +
//                    attributeExpressionExecutors[1].getReturnType().toString());
//        }
//        if (attributeExpressionExecutors[2].getReturnType() != Attribute.Type.STRING) {
//            throw new SiddhiAppValidationException("Invalid parameter type found for the third argument of " +
//                    "str:replaceAll() function, " + "required " + Attribute.Type.STRING + ", but found " +
//                    attributeExpressionExecutors[2].getReturnType().toString());
//        }
    }

    @Override
    protected Object execute(Object[] data) {
        String source = (String) data[0];
        Pattern p = Pattern.compile("(\\{\\{\\d}})");
        Matcher matcher = p.matcher(source);
        int i = 1;
        while (matcher.find()) {
            source = source.replace(matcher.group(0), (String) data[i++]);
        }
        return source;
    }

    @Override
    protected Object execute(Object data) {
        return null;  //Since the replaceAll function take 3 parameters, this method does not get called.
        // Hence, not implemented.
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    @Override
    public Map<String, Object> currentState() {
        return null;    //No need to maintain a state.
    }

    @Override
    public void restoreState(Map<String, Object> map) {

    }

}
