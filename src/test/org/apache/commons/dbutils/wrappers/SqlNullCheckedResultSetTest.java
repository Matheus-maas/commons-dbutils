/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//dbutils/src/test/org/apache/commons/dbutils/wrappers/SqlNullCheckedResultSetTest.java,v 1.1 2003/11/02 19:15:24 dgraham Exp $
 * $Revision: 1.1 $
 * $Date: 2003/11/02 19:15:24 $
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

package org.apache.commons.dbutils.wrappers;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ProxyFactory;

/**
 * Test cases for <code>SqlNullCheckedResultSet</code> class.
 *
 * @author  <a href="stevencaswell@apache.org">Steven Caswell</a>
 * @author David Graham
 * @version $Id: SqlNullCheckedResultSetTest.java,v 1.1 2003/11/02 19:15:24 dgraham Exp $
 */
public class SqlNullCheckedResultSetTest extends BaseTestCase {

    private ResultSet rs = null;

    private SqlNullCheckedResultSet rs2 = null;

    /**
     * Constructs a new instance of
     * <code>SqlNullCheckedResultSetTestCase</code>
     * with the specified name.
     *
     * @param name the test case name
     */
    public SqlNullCheckedResultSetTest(String name) {
        super(name);
    }

    /**
     * Sets up instance variables required by this test case.
     */
    public void setUp() throws Exception {
        super.setUp();

        rs2 =
            new SqlNullCheckedResultSet(
                ProxyFactory.instance().createResultSet(
                    new SqlNullUncheckedMockResultSet()));

        rs = ProxyFactory.instance().createResultSet(rs2);
    }

    /**
     * Tests the getAsciiStream implementation.
     */
    public void testGetAsciiStream() throws SQLException {

        assertNull(rs.getAsciiStream(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getAsciiStream("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        assertNotNull(rs.getAsciiStream(1));
        assertEquals(stream, rs.getAsciiStream(1));
        assertNotNull(rs.getAsciiStream("column"));
        assertEquals(stream, rs.getAsciiStream("column"));

    }

    /**
     * Tests the getBigDecimal implementation.
     */
    public void testGetBigDecimal() throws SQLException {

        assertNull(rs.getBigDecimal(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getBigDecimal("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        assertNotNull(rs.getBigDecimal(1));
        assertEquals(bd, rs.getBigDecimal(1));
        assertNotNull(rs.getBigDecimal("column"));
        assertEquals(bd, rs.getBigDecimal("column"));

    }

    /**
     * Tests the getBinaryStream implementation.
     */
    public void testGetBinaryStream() throws SQLException {

        assertNull(rs.getBinaryStream(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getBinaryStream("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        assertNotNull(rs.getBinaryStream(1));
        assertEquals(stream, rs.getBinaryStream(1));
        assertNotNull(rs.getBinaryStream("column"));
        assertEquals(stream, rs.getBinaryStream("column"));

    }

    /**
     * Tests the getBlob implementation.
     */
    public void testGetBlob() throws SQLException {

        assertNull(rs.getBlob(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getBlob("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        assertNotNull(rs.getBlob(1));
        assertEquals(blob, rs.getBlob(1));
        assertNotNull(rs.getBlob("column"));
        assertEquals(blob, rs.getBlob("column"));

    }

    /**
     * Tests the getBoolean implementation.
     */
    public void testGetBoolean() throws SQLException {

        assertEquals(false, rs.getBoolean(1));
        assertTrue(rs.wasNull());
        assertEquals(false, rs.getBoolean("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        assertEquals(true, rs.getBoolean(1));
        assertEquals(true, rs.getBoolean("column"));

    }

    /**
     * Tests the getByte implementation.
     */
    public void testGetByte() throws SQLException {

        assertEquals((byte) 0, rs.getByte(1));
        assertTrue(rs.wasNull());
        assertEquals((byte) 0, rs.getByte("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        assertEquals(b, rs.getByte(1));
        assertEquals(b, rs.getByte("column"));

    }

    /**
     * Tests the getByte implementation.
     */
    public void testGetBytes() throws SQLException {

        assertNull(rs.getBytes(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getBytes("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        assertNotNull(rs.getBytes(1));
        assertEquals(b, rs.getBytes(1));
        assertNotNull(rs.getBytes("column"));
        assertEquals(b, rs.getBytes("column"));

    }

    /**
     * Tests the getCharacterStream implementation.
     */
    public void testGetCharacterStream() throws SQLException {

        assertNull(rs.getCharacterStream(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getCharacterStream("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        assertNotNull(rs.getCharacterStream(1));
        assertEquals(reader, rs.getCharacterStream(1));
        assertNotNull(rs.getCharacterStream("column"));
        assertEquals(reader, rs.getCharacterStream("column"));

    }

    /**
     * Tests the getClob implementation.
     */
    public void testGetClob() throws SQLException {

        assertNull(rs.getClob(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getClob("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        assertNotNull(rs.getClob(1));
        assertEquals(clob, rs.getClob(1));
        assertNotNull(rs.getClob("column"));
        assertEquals(clob, rs.getClob("column"));

    }

    /**
     * Tests the getDate implementation.
     */
    public void testGetDate() throws SQLException {

        assertNull(rs.getDate(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getDate("column"));
        assertTrue(rs.wasNull());
        assertNull(rs.getDate(1, Calendar.getInstance()));
        assertTrue(rs.wasNull());
        assertNull(rs.getDate("column", Calendar.getInstance()));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        assertNotNull(rs.getDate(1));
        assertEquals(date, rs.getDate(1));
        assertNotNull(rs.getDate("column"));
        assertEquals(date, rs.getDate("column"));
        assertNotNull(rs.getDate(1, Calendar.getInstance()));
        assertEquals(date, rs.getDate(1, Calendar.getInstance()));
        assertNotNull(rs.getDate("column", Calendar.getInstance()));
        assertEquals(date, rs.getDate("column", Calendar.getInstance()));

    }

    /**
     * Tests the getDouble implementation.
     */
    public void testGetDouble() throws SQLException {

        assertEquals(0.0, rs.getDouble(1), 0.0);
        assertTrue(rs.wasNull());
        assertEquals(0.0, rs.getDouble("column"), 0.0);
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        double d = 10.0;
        rs2.setNullDouble(d);
        assertEquals(d, rs.getDouble(1), 0.0);
        assertEquals(d, rs.getDouble("column"), 0.0);

    }

    /**
     * Tests the getFloat implementation.
     */
    public void testGetFloat() throws SQLException {

        assertEquals((float) 0, rs.getFloat(1), 0.0);
        assertTrue(rs.wasNull());
        assertEquals((float) 0, rs.getFloat("column"), 0.0);
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        float f = (float) 10.0;
        rs2.setNullFloat(f);
        assertEquals(f, rs.getFloat(1), 0.0);
        assertEquals(f, rs.getFloat("column"), 0.0);

    }

    /**
     * Tests the getInt implementation.
     */
    public void testGetInt() throws SQLException {

        assertEquals(0, rs.getInt(1));
        assertTrue(rs.wasNull());
        assertEquals(0, rs.getInt("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        int i = 10;
        rs2.setNullInt(i);
        assertEquals(i, rs.getInt(1));
        assertEquals(i, rs.getInt("column"));

    }

    /**
     * Tests the getLong implementation.
     */
    public void testGetLong() throws SQLException {

        assertEquals((long) 0, rs.getLong(1));
        assertTrue(rs.wasNull());
        assertEquals((long) 0, rs.getLong("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        long l = (long) 10;
        rs2.setNullLong(l);
        assertEquals(l, rs.getLong(1));
        assertEquals(l, rs.getLong("column"));

    }

    /**
     * Tests the getObject implementation.
     */
    public void testGetObject() throws SQLException {

        assertNull(rs.getObject(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getObject("column"));
        assertTrue(rs.wasNull());
        assertNull(rs.getObject(1, (Map) null));
        assertTrue(rs.wasNull());
        assertNull(rs.getObject("column", (Map) null));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        assertNotNull(rs.getObject(1));
        assertEquals(o, rs.getObject(1));
        assertNotNull(rs.getObject("column"));
        assertEquals(o, rs.getObject("column"));
        assertNotNull(rs.getObject(1, (Map) null));
        assertEquals(o, rs.getObject(1, (Map) null));
        assertNotNull(rs.getObject("column", (Map) null));
        assertEquals(o, rs.getObject("column", (Map) null));

    }

    /**
     * Tests the getRef implementation.
     */
    public void testGetRef() throws SQLException {

        assertNull(rs.getRef(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getRef("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        assertNotNull(rs.getRef(1));
        assertEquals(ref, rs.getRef(1));
        assertNotNull(rs.getRef("column"));
        assertEquals(ref, rs.getRef("column"));

    }

    /**
     * Tests the getShort implementation.
     */
    public void testGetShort() throws SQLException {

        assertEquals((short) 0, rs.getShort(1));
        assertTrue(rs.wasNull());
        assertEquals((short) 0, rs.getShort("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        assertEquals(s, rs.getShort(1));
        assertEquals(s, rs.getShort("column"));

    }

    /**
     * Tests the getString implementation.
     */
    public void testGetString() throws SQLException {

        assertEquals(null, rs.getString(1));
        assertTrue(rs.wasNull());
        assertEquals(null, rs.getString("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        assertEquals(s, rs.getString(1));
        assertEquals(s, rs.getString("column"));

    }

    /**
     * Tests the getTime implementation.
     */
    public void testGetTime() throws SQLException {

        assertNull(rs.getTime(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getTime("column"));
        assertTrue(rs.wasNull());
        assertNull(rs.getTime(1, Calendar.getInstance()));
        assertTrue(rs.wasNull());
        assertNull(rs.getTime("column", Calendar.getInstance()));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        assertNotNull(rs.getTime(1));
        assertEquals(time, rs.getTime(1));
        assertNotNull(rs.getTime("column"));
        assertEquals(time, rs.getTime("column"));
        assertNotNull(rs.getTime(1, Calendar.getInstance()));
        assertEquals(time, rs.getTime(1, Calendar.getInstance()));
        assertNotNull(rs.getTime("column", Calendar.getInstance()));
        assertEquals(time, rs.getTime("column", Calendar.getInstance()));

    }

    /**
     * Tests the getTimestamp implementation.
     */
    public void testGetTimestamp() throws SQLException {

        assertNull(rs.getTimestamp(1));
        assertTrue(rs.wasNull());
        assertNull(rs.getTimestamp("column"));
        assertTrue(rs.wasNull());
        assertNull(rs.getTimestamp(1, Calendar.getInstance()));
        assertTrue(rs.wasNull());
        assertNull(rs.getTimestamp("column", Calendar.getInstance()));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        assertNotNull(rs.getTimestamp(1));
        assertEquals(ts, rs.getTimestamp(1));
        assertNotNull(rs.getTimestamp("column"));
        assertEquals(ts, rs.getTimestamp("column"));
        assertNotNull(rs.getTimestamp(1, Calendar.getInstance()));
        assertEquals(ts, rs.getTimestamp(1, Calendar.getInstance()));
        assertNotNull(rs.getTimestamp("column", Calendar.getInstance()));
        assertEquals(ts, rs.getTimestamp("column", Calendar.getInstance()));

    }

    /**
     * Tests the setNullAsciiStream implementation.
     */
    public void testSetNullAsciiStream() throws SQLException {

        assertNull(rs2.getNullAsciiStream());
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        assertNotNull(rs.getAsciiStream(1));
        assertEquals(stream, rs.getAsciiStream(1));
        assertNotNull(rs.getAsciiStream("column"));
        assertEquals(stream, rs.getAsciiStream("column"));

    }

    /**
     * Tests the setNullBigDecimal implementation.
     */
    public void testSetNullBigDecimal() throws SQLException {

        assertNull(rs2.getNullBigDecimal());
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        assertNotNull(rs.getBigDecimal(1));
        assertEquals(bd, rs.getBigDecimal(1));
        assertNotNull(rs.getBigDecimal("column"));
        assertEquals(bd, rs.getBigDecimal("column"));

    }

    /**
     * Tests the setNullBinaryStream implementation.
     */
    public void testSetNullBinaryStream() throws SQLException {

        assertNull(rs2.getNullBinaryStream());
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        assertNotNull(rs.getBinaryStream(1));
        assertEquals(stream, rs.getBinaryStream(1));
        assertNotNull(rs.getBinaryStream("column"));
        assertEquals(stream, rs.getBinaryStream("column"));

    }

    /**
     * Tests the setNullBlob implementation.
     */
    public void testSetNullBlob() throws SQLException {

        assertNull(rs2.getNullBlob());
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        assertNotNull(rs.getBlob(1));
        assertEquals(blob, rs.getBlob(1));
        assertNotNull(rs.getBlob("column"));
        assertEquals(blob, rs.getBlob("column"));

    }

    /**
     * Tests the setNullBoolean implementation.
     */
    public void testSetNullBoolean() throws SQLException {

        assertEquals(false, rs2.getNullBoolean());
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        assertEquals(true, rs.getBoolean(1));
        assertEquals(true, rs.getBoolean("column"));

    }

    /**
     * Tests the setNullByte implementation.
     */
    public void testSetNullByte() throws SQLException {

        assertEquals((byte) 0, rs2.getNullByte());
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        assertEquals(b, rs.getByte(1));
        assertEquals(b, rs.getByte("column"));

    }

    /**
     * Tests the setNullByte implementation.
     */
    public void testSetNullBytes() throws SQLException {

        assertNull(rs2.getNullBytes());
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        assertNotNull(rs.getBytes(1));
        assertEquals(b, rs.getBytes(1));
        assertNotNull(rs.getBytes("column"));
        assertEquals(b, rs.getBytes("column"));

    }

    /**
     * Tests the setNullCharacterStream implementation.
     */
    public void testSetNullCharacterStream() throws SQLException {

        assertNull(rs2.getNullCharacterStream());
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        assertNotNull(rs.getCharacterStream(1));
        assertEquals(reader, rs.getCharacterStream(1));
        assertNotNull(rs.getCharacterStream("column"));
        assertEquals(reader, rs.getCharacterStream("column"));

    }

    /**
     * Tests the setNullClob implementation.
     */
    public void testSetNullClob() throws SQLException {

        assertNull(rs2.getNullClob());
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        assertNotNull(rs.getClob(1));
        assertEquals(clob, rs.getClob(1));
        assertNotNull(rs.getClob("column"));
        assertEquals(clob, rs.getClob("column"));

    }

    /**
     * Tests the setNullDate implementation.
     */
    public void testSetNullDate() throws SQLException {

        assertNull(rs2.getNullDate());
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        assertNotNull(rs.getDate(1));
        assertEquals(date, rs.getDate(1));
        assertNotNull(rs.getDate("column"));
        assertEquals(date, rs.getDate("column"));
        assertNotNull(rs.getDate(1, Calendar.getInstance()));
        assertEquals(date, rs.getDate(1, Calendar.getInstance()));
        assertNotNull(rs.getDate("column", Calendar.getInstance()));
        assertEquals(date, rs.getDate("column", Calendar.getInstance()));

    }

    /**
     * Tests the setNullDouble implementation.
     */
    public void testSetNullDouble() throws SQLException {

        assertEquals((double) 0.0, rs2.getNullDouble(), 0.0);
        // Set what gets returned to something other than the default
        double d = (double) 10.0;
        rs2.setNullDouble(d);
        assertEquals(d, rs.getDouble(1), 0.0);
        assertEquals(d, rs.getDouble("column"), 0.0);

    }

    /**
     * Tests the setNullFloat implementation.
     */
    public void testSetNullFloat() throws SQLException {

        assertEquals((float) 0.0, rs2.getNullFloat(), 0.0);
        // Set what gets returned to something other than the default
        float f = (float) 10.0;
        rs2.setNullFloat(f);
        assertEquals(f, rs.getFloat(1), 0.0);
        assertEquals(f, rs.getFloat("column"), 0.0);

    }

    /**
     * Tests the setNullInt implementation.
     */
    public void testSetNullInt() throws SQLException {

        assertEquals(0, rs2.getNullInt());
        assertEquals((int) 0, rs.getInt(1));
        assertTrue(rs.wasNull());
        assertEquals((int) 0, rs.getInt("column"));
        assertTrue(rs.wasNull());
        // Set what gets returned to something other than the default
        int i = (int) 10;
        rs2.setNullInt(i);
        assertEquals(i, rs.getInt(1));
        assertEquals(i, rs.getInt("column"));

    }

    /**
     * Tests the setNullLong implementation.
     */
    public void testSetNullLong() throws SQLException {

        assertEquals((long) 0, rs2.getNullLong());
        // Set what gets returned to something other than the default
        long l = (long) 10;
        rs2.setNullLong(l);
        assertEquals(l, rs.getLong(1));
        assertEquals(l, rs.getLong("column"));

    }

    /**
     * Tests the setNullObject implementation.
     */
    public void testSetNullObject() throws SQLException {

        assertNull(rs2.getNullObject());
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        assertNotNull(rs.getObject(1));
        assertEquals(o, rs.getObject(1));
        assertNotNull(rs.getObject("column"));
        assertEquals(o, rs.getObject("column"));
        assertNotNull(rs.getObject(1, (Map) null));
        assertEquals(o, rs.getObject(1, (Map) null));
        assertNotNull(rs.getObject("column", (Map) null));
        assertEquals(o, rs.getObject("column", (Map) null));

    }

    /**
     * Tests the setNullShort implementation.
     */
    public void testSetNullShort() throws SQLException {

        assertEquals((short) 0, rs2.getNullShort());
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        assertEquals(s, rs.getShort(1));
        assertEquals(s, rs.getShort("column"));

    }

    /**
     * Tests the setNullString implementation.
     */
    public void testSetNullString() throws SQLException {

        assertEquals(null, rs2.getNullString());
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        assertEquals(s, rs.getString(1));
        assertEquals(s, rs.getString("column"));

    }

    /**
     * Tests the setNullRef implementation.
     */
    public void testSetNullRef() throws SQLException {

        assertNull(rs2.getNullRef());
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        assertNotNull(rs.getRef(1));
        assertEquals(ref, rs.getRef(1));
        assertNotNull(rs.getRef("column"));
        assertEquals(ref, rs.getRef("column"));

    }

    /**
     * Tests the setNullTime implementation.
     */
    public void testSetNullTime() throws SQLException {

        assertEquals(null, rs2.getNullTime());
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        assertNotNull(rs.getTime(1));
        assertEquals(time, rs.getTime(1));
        assertNotNull(rs.getTime("column"));
        assertEquals(time, rs.getTime("column"));
        assertNotNull(rs.getTime(1, Calendar.getInstance()));
        assertEquals(time, rs.getTime(1, Calendar.getInstance()));
        assertNotNull(rs.getTime("column", Calendar.getInstance()));
        assertEquals(time, rs.getTime("column", Calendar.getInstance()));

    }

    /**
     * Tests the setNullTimestamp implementation.
     */
    public void testSetNullTimestamp() throws SQLException {

        assertEquals(null, rs2.getNullTimestamp());
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        assertNotNull(rs.getTimestamp(1));
        assertEquals(ts, rs.getTimestamp(1));
        assertNotNull(rs.getTimestamp("column"));
        assertEquals(ts, rs.getTimestamp("column"));
        assertNotNull(rs.getTimestamp(1, Calendar.getInstance()));
        assertEquals(ts, rs.getTimestamp(1, Calendar.getInstance()));
        assertNotNull(rs.getTimestamp("column", Calendar.getInstance()));
        assertEquals(ts, rs.getTimestamp("column", Calendar.getInstance()));

    }
}

class SqlNullUncheckedMockResultSet implements InvocationHandler {

    /**
     * Always return false for booleans, 0 for numerics, and null for Objects.
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        Class returnType = method.getReturnType();

        if (method.getName().equals("wasNull")) {
            return Boolean.TRUE;

        } else if (returnType.equals(Boolean.TYPE)) {
            return Boolean.FALSE;

        } else if (returnType.equals(Integer.TYPE)) {
            return new Integer(0);

        } else if (returnType.equals(Short.TYPE)) {
            return new Short((short) 0);

        } else if (returnType.equals(Double.TYPE)) {
            return new Double(0);

        } else if (returnType.equals(Long.TYPE)) {
            return new Long(0);

        } else if (returnType.equals(Byte.TYPE)) {
            return new Byte((byte) 0);

        } else if (returnType.equals(Float.TYPE)) {
            return new Float(0);

        } else {
            return null;
        }
    }
}

class SqlNullCheckedResultSetMockBlob implements Blob {

    public InputStream getBinaryStream() throws SQLException {
        return new ByteArrayInputStream(new byte[0]);
    }

    public byte[] getBytes(long param, int param1) throws SQLException {
        return new byte[0];
    }

    public long length() throws SQLException {
        return 0;
    }

    public long position(byte[] values, long param) throws SQLException {
        return 0;
    }

    public long position(Blob blob, long param) throws SQLException {
        return 0;
    }

    public void truncate(long len) throws SQLException {

    }

    public int setBytes(long pos, byte[] bytes) throws SQLException {
        return 0;
    }

    public int setBytes(long pos, byte[] bytes, int offset, int len)
        throws SQLException {
        return 0;
    }

    public OutputStream setBinaryStream(long pos) throws SQLException {
        return null;
    }

}

class SqlNullCheckedResultSetMockClob implements Clob {

    public InputStream getAsciiStream() throws SQLException {
        return null;
    }

    public Reader getCharacterStream() throws SQLException {
        return null;
    }

    public String getSubString(long param, int param1) throws SQLException {
        return "";
    }

    public long length() throws SQLException {
        return 0;
    }

    public long position(Clob clob, long param) throws SQLException {
        return 0;
    }

    public long position(String str, long param) throws SQLException {
        return 0;
    }

    public void truncate(long len) throws SQLException {

    }

    public OutputStream setAsciiStream(long pos) throws SQLException {
        return null;
    }

    public Writer setCharacterStream(long pos) throws SQLException {
        return null;
    }

    public int setString(long pos, String str) throws SQLException {
        return 0;
    }

    public int setString(long pos, String str, int offset, int len)
        throws SQLException {
        return 0;
    }

}

class SqlNullCheckedResultSetMockRef implements Ref {

    public String getBaseTypeName() throws SQLException {
        return "";
    }

    public Object getObject() throws SQLException {
        return null;
    }

    public void setObject(Object value) throws SQLException {

    }

    public Object getObject(Map map) throws SQLException {
        return null;
    }

}