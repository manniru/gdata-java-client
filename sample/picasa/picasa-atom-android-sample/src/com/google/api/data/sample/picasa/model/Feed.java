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

package com.google.api.data.sample.picasa.model;

import com.google.api.client.googleapis.GoogleTransport;
import com.google.api.client.googleapis.xml.atom.GData;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.util.Name;

import java.io.IOException;
import java.util.List;

public class Feed {

  @Name("link")
  public List<Link> links;

  public String getPostLink() {
    return Link.find(links, "http://schemas.google.com/g/2005#post");
  }

  public String getNextLink() {
    return Link.find(links, "next");
  }

  static Feed executeGet(GoogleTransport transport, PicasaUri uri,
      Class<? extends Feed> feedClass) throws IOException {
    uri.fields = GData.getFieldsFor(feedClass);
    HttpRequest request = transport.buildGetRequest(uri.build());
    return request.execute().parseAs(feedClass);
  }
}
