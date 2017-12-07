/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.User;
import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jvm
 */
public class RoleUserTest {
    
    public RoleUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRole method, of class RoleUser.
     */
    @Test
    public void testGetRole_HttpServletRequest() {
        System.out.println("getRole");
        HttpServletRequest request = null;
        RoleUser instance = new RoleUser();
        String expResult = "";
        String result = instance.getRole(request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class RoleUser.
     */
    @Test
    public void testGetRole_User() {
        System.out.println("getRole");
        User user = null;
        RoleUser instance = new RoleUser();
        String expResult = "";
        String result = instance.getRole(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class RoleUser.
     */
    @Test
    public void testGetRole_String() {
        System.out.println("getRole");
        String userId = "";
        RoleUser instance = new RoleUser();
        String expResult = "";
        String result = instance.getRole(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class RoleUser.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String role = "";
        User user = null;
        RoleUser instance = new RoleUser();
        boolean expResult = false;
        boolean result = instance.contains(role, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
