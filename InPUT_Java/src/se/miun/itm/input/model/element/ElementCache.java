/*-- $Copyright (C) 2012 Felix Dobslaw$


Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished
to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package se.miun.itm.input.model.element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Element;

import se.miun.itm.input.model.InPUTException;
import se.miun.itm.input.model.param.Param;

/**
 * The element cache is a data-structure that holds references to all current
 * values for each parameter. This is necessary for the dynamic parameter
 * evaluations based on dependencies and a fast response for user requests.
 * 
 * The scope of an element cache can be extended to other caches.
 * @author Felix Dobslaw
 * 
 */
public class ElementCache {

	private Map<String, Value<? extends Param>> cache = new HashMap<String, Value<? extends Param>>();

	private List<ElementCache> neighbors = new ArrayList<ElementCache>();

	public void setReadOnly() {
		cache = Collections.unmodifiableMap(cache);
	}

	public Value<? extends Param> get(String paramId) {
		Value<? extends Param> value = cache.get(paramId);

		if (value == null)
			for (ElementCache neighbor : neighbors) {
				value = neighbor.get(paramId);
				if (value != null)
					break;
			}

		return value;
	}

	public void put(String id, Value<? extends Param> valueE) {
		cache.put(id, valueE);
	}

	public boolean containsKey(String paramId) {
		return cache.containsKey(paramId);
	}

	public boolean containsKeyInScope(String paramId) {
		if (cache.containsKey(paramId))
			return true;

		for (ElementCache neighbor : neighbors)
			if (neighbor.containsKey(paramId))
				return true;

		return false;
	}

	public void remove(String id) {
		cache.remove(id);
	}

	public Set<String> getSupportedParamIds() {
		return Collections.unmodifiableSet(cache.keySet());
	}

	public Set<String> getSupportedParamIdsInScope() {
		Set<String> keys = new HashSet<String>(cache.keySet());
		for (ElementCache neighbor : neighbors)
			keys.addAll(neighbor.getSupportedParamIds());
		return Collections.unmodifiableSet(keys);
	}

	public void extendScope(ElementCache cache) {
		neighbors.add(cache);
	}

	public void updateCache(Value<?> element) throws InPUTException {
		Value<?> upper = element;
		Element parent = upper.getParentElement();
		if (parent instanceof Value<?>) {
			upper = (Value<?>) parent;
			upper.init(null);
		}
	}
}