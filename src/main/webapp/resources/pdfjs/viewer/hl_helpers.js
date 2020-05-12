
// https://stackoverflow.com/questions/901115/how-can-i-get-query-string-values-in-javascript
function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

var hlCopy = getParameterByName('hlCopy');
if(hlCopy === '1' || hlCopy === 'true') {
  var compressWs = getParameterByName('wsc') === '1';

  var textSelectionStart, textSelectionEnd;

  // A hook receiving selected block start/end
  function onTextSelectionChange(start, end) {
    textSelectionStart = start;
    textSelectionEnd = end;
    console.log('Selected text', start, end);
    //copySelectionToClipboard(); // to get text as soon as it's selected
  }

  // Listener for COPY
  document.addEventListener('copy', function(e){
    // e.preventDefault(); // We could disable basic behavior but leave it as a fallback
    console.log('COPY');
    copySelectionToClipboard();
  });

  function copySelectionToClipboard() {
    if (textSelectionStart && textSelectionEnd) {
      getSelectedTextFromHighlighter(getPdfFileUrl(), textSelectionStart, textSelectionEnd, function (response) {
        console.log('Received response', response);
        if (response.success && response.sections.length > 0) {
          var text = response.sections[0];
          if (compressWs)
            text = text.replace(/\s+/g, ' '); // replace one or more white-space chars with one space
          console.log('Text to copy:', text);
          if (text && text.length > 0)
            copyTextToClipboard(text);
        }
      })
    }
  }

  function getSelectedTextFromHighlighter(file, start, end, callback) {
    var xhr = new XMLHttpRequest();
    var url = (getParameterByName('hlSrv') || '..') + "/extract/area" + // URL to Highlighter's extract service
      '?uri=' + encodeURIComponent(file);
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
        callback(json);
      }
    };
    var data = JSON.stringify({
      sections: [{start: start, end: end}]
    });
    xhr.send(data);
  }

  function getPdfFileUrl() {
    return getParameterByName('file');
  }

  // Credits to https://stackoverflow.com/questions/400212/how-do-i-copy-to-the-clipboard-in-javascript
  function fallbackCopyTextToClipboard(text) {
    var textArea = document.createElement("textarea");
    textArea.value = text;
    document.body.appendChild(textArea);
    textArea.focus();
    textArea.select();

    try {
      var successful = document.execCommand('copy');
      var msg = successful ? 'successful' : 'unsuccessful';
      console.log('Fallback: Copying text command was ' + msg);
    } catch (err) {
      console.error('Fallback: Oops, unable to copy', err);
    }

    document.body.removeChild(textArea);
  }
  function copyTextToClipboard(text) {
    if (!navigator.clipboard) {
      fallbackCopyTextToClipboard(text);
      return;
    }
    navigator.clipboard.writeText(text).then(function() {
      console.log('Async: Copying to clipboard was successful!');
    }, function(err) {
      console.error('Async: Could not copy text: ', err);
      fallbackCopyTextToClipboard(text);
    });
  }
}

// credits to http://stackoverflow.com/questions/524696/how-to-create-a-style-tag-with-javascript/524798#524798
if(typeof document.createStyleSheet === 'undefined') {
    document.createStyleSheet = (function() {
        function createStyleSheet(href) {
            if(typeof href !== 'undefined') {
                var element = document.createElement('link');
                element.type = 'text/css';
                element.rel = 'stylesheet';
                element.href = href;
            }
            else {
                var element = document.createElement('style');
                element.type = 'text/css';
            }

            document.getElementsByTagName('head')[0].appendChild(element);
            var sheet = document.styleSheets[document.styleSheets.length - 1];

            if(typeof sheet.addRule === 'undefined')
                sheet.addRule = addRule;

            if(typeof sheet.removeRule === 'undefined')
                sheet.removeRule = sheet.deleteRule;

            return sheet;
        }

        function addRule(selectorText, cssText, index) {
            if(typeof index === 'undefined')
                index = this.cssRules.length;

            this.insertRule(selectorText + ' {' + cssText + '}', index);
        }

        return createStyleSheet;
    })();
}

// Not using any more as there's Util.loadScript()
// if(typeof document.loadScript === 'undefined') {
//   document.loadScript = function(src) {
//     var element = document.createElement('script');
//     element.type = 'text/javascript';
//     element.src = src;
//     document.getElementsByTagName('head')[0].appendChild(element);
//   };
// }

function PdfUtil(url) {

    var iframe;

    var __construct = function(url) {
        iframe = getContentIframe(url);
    }

    var getContentIframe = function(url) {
        var iframe = document.createElement('iframe');
        iframe.src = url;
        return iframe;
    }

    this.display = function(parentDomElement) {
        parentDomElement.appendChild(iframe);
    }

    this.print = function() {
        try {
            iframe.contentWindow.print();
        } catch(e) {
            throw new Error("Printing failed. " + e);
        }
    }

    __construct(url);
}

var CookieUtils = {

  // cookie functions borrowed from http://stackoverflow.com/questions/14573223/set-cookie-and-get-cookie-with-javascript

  createCookie: function(name,value,days) {
    if (days) {
      var date = new Date();
      date.setTime(date.getTime()+(days*24*60*60*1000));
      var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
  },

  readCookie: function(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
      var c = ca[i];
      while (c.charAt(0)==' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
  },

  eraseCookie: function(name) {
    this.createCookie(name,"",-1);
  }

};


//******** Polyfills

// https://tc39.github.io/ecma262/#sec-array.prototype.includes
if (!Array.prototype.includes) {
  Object.defineProperty(Array.prototype, 'includes', {
    value: function(searchElement, fromIndex) {

      if (this == null) {
        throw new TypeError('"this" is null or not defined');
      }

      // 1. Let O be ? ToObject(this value).
      var o = Object(this);

      // 2. Let len be ? ToLength(? Get(O, "length")).
      var len = o.length >>> 0;

      // 3. If len is 0, return false.
      if (len === 0) {
        return false;
      }

      // 4. Let n be ? ToInteger(fromIndex).
      //    (If fromIndex is undefined, this step produces the value 0.)
      var n = fromIndex | 0;

      // 5. If n ≥ 0, then
      //  a. Let k be n.
      // 6. Else n < 0,
      //  a. Let k be len + n.
      //  b. If k < 0, let k be 0.
      var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

      function sameValueZero(x, y) {
        return x === y || (typeof x === 'number' && typeof y === 'number' && isNaN(x) && isNaN(y));
      }

      // 7. Repeat, while k < len
      while (k < len) {
        // a. Let elementK be the result of ? Get(O, ! ToString(k)).
        // b. If SameValueZero(searchElement, elementK) is true, return true.
        if (sameValueZero(o[k], searchElement)) {
          return true;
        }
        // c. Increase k by 1.
        k++;
      }

      // 8. Return false
      return false;
    }
  });
}

if (!String.prototype.includes) {
  String.prototype.includes = function(search, start) {
    'use strict';
    if (typeof start !== 'number') {
      start = 0;
    }

    if (start + search.length > this.length) {
      return false;
    } else {
      return this.indexOf(search, start) !== -1;
    }
  };
}

if (!String.prototype.endsWith) {
  String.prototype.endsWith = function(search, this_len) {
    if (this_len === undefined || this_len > this.length) {
      this_len = this.length;
    }
    return this.substring(this_len - search.length, this_len) === search;
  };
}
