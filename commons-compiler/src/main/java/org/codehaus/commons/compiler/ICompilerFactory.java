
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

package org.codehaus.commons.compiler;

/**
 * An entity that produces implementations of {@link IExpressionEvaluator}, {@link IScriptEvaluator}, {@link
 * IClassBodyEvaluator}, {@link ISimpleCompiler} or {@link AbstractJavaSourceClassLoader}.
 * <p>
 *   Implementations of this interface are, e.g. {@code org.codehaus.janino} and {@code
 *   org.codehaus.commons.compiler.jdk}.
 * </p>
 */
public
interface ICompilerFactory {

    /**
     * @return A {@link String} which uniquely identifies the concrete implementation of this interface, e.g.
     *         {@code "org.codehaus.janino"} or {@code "org.codehaus.commons.compiler.jdk"}
     */
    String getId();

    /**
     * @return A human-readable {@link String} that identifies the concrete implementation of this interface in a user
     *         interface, e.g. {@code "janino"} or {@code "jdk"}
     */
    @Override String toString();

    /**
     * @return The version of <em>this</em> implementation of the commons-compiler specification, or {@code null}
     */
    String getImplementationVersion();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       IExpressionEvaluator}
     * @see IExpressionEvaluator
     */
    IExpressionEvaluator newExpressionEvaluator();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       IScriptEvaluator}
     * @see IScriptEvaluator
     */
    IScriptEvaluator newScriptEvaluator();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       IClassBodyEvaluator}
     * @see IClassBodyEvaluator
     */
    IClassBodyEvaluator newClassBodyEvaluator();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       ISimpleCompiler}
     * @see ISimpleCompiler
     */
    ISimpleCompiler newSimpleCompiler();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       AbstractJavaSourceClassLoader}
     * @see AbstractJavaSourceClassLoader
     */
    AbstractJavaSourceClassLoader newJavaSourceClassLoader();

    /**
     * @throws UnsupportedOperationException The underlying implementation does not implement an {@link
     *                                       AbstractJavaSourceClassLoader}
     * @see AbstractJavaSourceClassLoader
     */
    AbstractJavaSourceClassLoader newJavaSourceClassLoader(ClassLoader parentClassLoader);
}
