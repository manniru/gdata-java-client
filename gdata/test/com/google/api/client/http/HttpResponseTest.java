/*
 * Copyright (c) 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.client.http;

import com.google.api.client.util.Key;

import junit.framework.TestCase;

import java.io.IOException;

/**
 * Tests {@link HttpResponse}.
 * 
 * @author Yaniv Inbar
 */
public class HttpResponseTest extends TestCase {

  public HttpResponseTest() {
  }

  public HttpResponseTest(String name) {
    super(name);
  }

  public void testParseAsString_none() throws IOException {
    HttpTransport transport = new HttpTransport();
    LowLevelHttpResponse lowResponse = new MockLowLevelHttpResponse();
    HttpResponse response = new HttpResponse(transport, lowResponse);
    assertEquals("", response.parseAsString());
  }

  public static class MyHeaders extends HttpHeaders {
    @Key
    public String foo;
  }

  public void testHeaderParsing() {
    HttpTransport transport = new HttpTransport();
    transport.defaultHeaders = new MyHeaders();
    MockLowLevelHttpResponse lowResponse = new MockLowLevelHttpResponse();
    lowResponse.headers.add("accept", "value");
    lowResponse.headers.add("foo", "bar");
    lowResponse.headers.add("goo", "car");
    HttpResponse response = new HttpResponse(transport, lowResponse);
    assertEquals("value", response.headers.accept);
    assertEquals("bar", ((MyHeaders) response.headers).foo);
    assertEquals("car", response.headers.get("goo"));
  }
}
