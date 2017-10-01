package io.github.mattshen.dakit.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zhongweishen on 1/10/17.
 */
public class StringUtilsTest {
    @Test
    public void trim() throws Exception {

        Assert.assertEquals("", StringUtils.trim(""));
        Assert.assertNull(StringUtils.trim(null));
        Assert.assertEquals("abc", StringUtils.trim(" abc"));

    }

    @Test
    public void isInteger() throws Exception {
        Assert.assertTrue(StringUtils.isNumber("123"));
        Assert.assertTrue(StringUtils.isNumber("43284730924893824823904890238490823408392048"));
        Assert.assertFalse(StringUtils.isNumber("abc"));
        Assert.assertFalse(StringUtils.isNumber("3123213abc"));
    }

}