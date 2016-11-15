
/*
 * Janino - An embedded Java[TM] compiler
 *
 * Copyright (c) 2001-2010 Arno Unkrig. All rights reserved.
 * Copyright (c) 2015-2016 TIBCO Software Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *       following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 *       following disclaimer in the documentation and/or other materials provided with the distribution.
 *    3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote
 *       products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.codehaus.janino.tests;

import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.commons.compiler.IExpressionEvaluator;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.ScriptEvaluator;
import org.junit.Assert;
import org.junit.Test;

// CHECKSTYLE JavadocMethod:OFF

/**
 * Unit tests fot the {@link ExpressionEvaluator}.
 */
public
class ExpressionEvaluatorTest {

    @Test public void
    testGuessParameterNames() throws Exception {
        Set<String> parameterNames = new HashSet<String>(
            Arrays.asList(ExpressionEvaluator.guessParameterNames(new Scanner(null, new StringReader(
                ""
                + "import o.p;\n"
                + "a + b.c + d.e() + f() + g.h.I.j() + k.l.M"
            ))))
        );
        Assert.assertEquals(new HashSet<String>(Arrays.asList("a", "b", "d")), parameterNames);

        parameterNames = new HashSet<String>(
            Arrays.asList(ScriptEvaluator.guessParameterNames(new Scanner(null, new StringReader(
                ""
                + "import o.p;\n"
                + "int a;\n"
                + "return a + b.c + d.e() + f() + g.h.I.j() + k.l.M;"
            ))))
        );
        Assert.assertEquals(new HashSet<String>(Arrays.asList("b", "d")), parameterNames);
    }

    @Test public void
    testAnyType1() throws Exception {

        @SuppressWarnings("deprecation") Class<?> anyType = IExpressionEvaluator.ANY_TYPE;

        ExpressionEvaluator ee = new ExpressionEvaluator();
        ee.setExpressionType(anyType);

        ee.cook("3");
        Assert.assertEquals(3, ee.evaluate(null));

        ee.cook("\"HELLO\"");
        Assert.assertEquals("HELLO", ee.evaluate(null));
    }

    @Test public void
    testAnyType2() throws Exception {

        ExpressionEvaluator ee = new ExpressionEvaluator();
        ee.setExpressionType(Object.class);

        ee.cook("3");
        Assert.assertEquals(3, ee.evaluate(null));

        ee.cook("\"HELLO\"");
        Assert.assertEquals("HELLO", ee.evaluate(null));
    }
}
