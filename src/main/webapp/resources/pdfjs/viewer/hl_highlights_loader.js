


//simple XHR request in pure JavaScript
function load(url, callback) {
  var xhr;

  if(typeof XMLHttpRequest !== 'undefined') xhr = new XMLHttpRequest();
  else {
    var versions = ["MSXML2.XmlHttp.5.0",
      "MSXML2.XmlHttp.4.0",
      "MSXML2.XmlHttp.3.0",
      "MSXML2.XmlHttp.2.0",
      "Microsoft.XmlHttp"];

    for(var i = 0, len = versions.length; i < len; i++) {
      try {
        xhr = new ActiveXObject(versions[i]);
        break;
      }
      catch(e){}
    } // end for
  }

  xhr.onreadystatechange = ensureReadiness;

  function ensureReadiness() {
    if(xhr.readyState < 4) {
      return;
    }

    if(xhr.status !== 200) {
      return;
    }

    // all is well
    if(xhr.readyState === 4) {
      callback(xhr);
    }
  }

  xhr.open('GET', url, true);
  xhr.setRequestHeader("Accept","application/json");
  xhr.send('');
}

var cachedResponses = {};

self.addEventListener('message', function(e) {
  if (e.data.url) {

    // console.log('LOADER REQUESTED', e.data.url);
    var highlights = cachedResponses[e.data.url];
    if (highlights) {
      // console.log('WORKER found hl in cache for', e.data.url, highlights);
      if (highlights.requestInProgress !== true) // send upstream only if we have a complete response
        sendHighlightsUpstream(highlights);
      return;
    }
    else {
      cachedResponses[e.data.url] = {requestInProgress: true}; // marking request in progress
    }

    load(e.data.url, function(xhr) {
      try {
        // console.log('WORKER RECEIVED', xhr.responseText);
        highlights = JSON.parse(xhr.responseText);
        highlights['highlightsInfoUrl'] = e.data.url;
        cachedResponses[e.data.url] = highlights;
        sendHighlightsUpstream(highlights);
      }
      catch(e) {
        console.error('Error parsing highlighting response. ' + e.message);
      }
    });
  }

  function sendHighlightsUpstream(highlights) {
    self.postMessage(highlights);
  }
}, false);

