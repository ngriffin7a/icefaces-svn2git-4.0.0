/*
 * Copyright 2004-2014 ICEsoft Technologies Canada Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

(function() {
    var elementRemoveListeners = [];
    namespace.onElementRemove = function(id, callback) {
        append(elementRemoveListeners, {i: id, c: callback});
        return removeCallbackCallback(elementRemoveListeners, detectByReference(callback));
    };

    namespace.onAfterUpdate(function() {
        each(elementRemoveListeners, function(tuple) {
            var notFound = true;
            try {
                notFound = !document.getElementById(tuple.i);
            } catch (ex) {
                notFound = true;
            }
            if (notFound) {
                try {
                    tuple.c();
                } catch (ex) {
                    warn(logger, 'failed to execute onElementRemove callback for element ' + tuple.i);
                }
            }
        });
    });
})();

