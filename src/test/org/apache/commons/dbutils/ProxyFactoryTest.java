/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//dbutils/src/test/org/apache/commons/dbutils/ProxyFactoryTest.java,v 1.1 2003/11/02 19:15:23 dgraham Exp $
 * $Revision: 1.1 $
 * $Date: 2003/11/02 19:15:23 $
 * 
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.dbutils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * ProxyFactoryTest performs simple type checking on proxy objects returned
 * from a ProxyFactory.
 * 
 * @author David Graham
 */
public class ProxyFactoryTest extends BaseTestCase {

	private static final InvocationHandler stub = new InvocationHandler() {

		public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

			return null;
		}
	};

	/**
	 * Constructor for ProxyFactoryTest.
	 */
	public ProxyFactoryTest(String name) {
		super(name);
	}

	public void testCreateConnection() {
		assertTrue(
			ProxyFactory.instance().createConnection(stub)
				instanceof Connection);
	}

	public void testCreateDriver() {
		assertTrue(
			ProxyFactory.instance().createDriver(stub) instanceof Driver);
	}

	public void testCreatePreparedStatement() {
		assertTrue(
			ProxyFactory.instance().createPreparedStatement(stub)
				instanceof PreparedStatement);
	}

	public void testCreateResultSet() {
		assertTrue(
			ProxyFactory.instance().createResultSet(stub) instanceof ResultSet);
	}

	public void testCreateResultSetMetaData() {
		assertTrue(
			ProxyFactory.instance().createResultSetMetaData(stub)
				instanceof ResultSetMetaData);
	}

	public void testCreateStatement() {
		assertTrue(
			ProxyFactory.instance().createStatement(stub) instanceof Statement);
	}

	public void testCreateCallableStatement() {
		assertTrue(
			ProxyFactory.instance().createCallableStatement(stub)
				instanceof CallableStatement);
	}

}