/* Lab System 浏览器兼容 polyfill (支持 Chromium 86+ / 旧版360 / 鸿蒙 / 安卓 WebView) */
(function () {
  'use strict';
  // ================= Array.prototype =================
  var AP = Array.prototype;
  function def(name, fn) {
    if (!(name in AP)) {
      try { Object.defineProperty(AP, name, { value: fn, writable: true, configurable: true }); }
      catch (e) { AP[name] = fn; }
    }
  }
  // Array.prototype.at (Chrome 92+)
  def('at', function (n) {
    n = Number(n) || 0;
    var len = this.length;
    var k = n >= 0 ? n : len + n;
    return k >= 0 && k < len ? this[k] : undefined;
  });
  // findLast / findLastIndex (Chrome 97+)
  def('findLast', function (cb, thisArg) {
    for (var i = this.length - 1; i >= 0; i--) {
      if (cb.call(thisArg, this[i], i, this)) return this[i];
    }
    return undefined;
  });
  def('findLastIndex', function (cb, thisArg) {
    for (var i = this.length - 1; i >= 0; i--) {
      if (cb.call(thisArg, this[i], i, this)) return i;
    }
    return -1;
  });
  // toSorted / toReversed / toSpliced / with (Chrome 110+)
  def('toSorted', function (cmp) {
    var arr = this.slice();
    arr.sort(typeof cmp === 'function' ? cmp : undefined);
    return arr;
  });
  def('toReversed', function () { return this.slice().reverse(); });
  def('toSpliced', function () {
    var args = Array.prototype.slice.call(arguments, 0);
    var arr = this.slice();
    arr.splice.apply(arr, args);
    return arr;
  });
  def('with', function (index, value) {
    var arr = this.slice();
    arr[index] = value;
    return arr;
  });
  // ================= Object =================
  if (!Object.hasOwn) {
    Object.hasOwn = function (obj, key) {
      return Object.prototype.hasOwnProperty.call(obj, key);
    };
  }
  // ================= structuredClone (Chrome 98+) =================
  if (typeof window !== 'undefined' && !window.structuredClone) {
    function deepClone(val) {
      if (val === null || typeof val !== 'object') return val;
      if (val instanceof Date) return new Date(val.getTime());
      if (val instanceof RegExp) return new RegExp(val.source, val.flags);
      if (typeof Map !== 'undefined' && val instanceof Map) {
        var m = new Map();
        val.forEach(function (v, k) { m.set(k, deepClone(v)); });
        return m;
      }
      if (typeof Set !== 'undefined' && val instanceof Set) {
        var s = new Set();
        val.forEach(function (v) { s.add(deepClone(v)); });
        return s;
      }
      if (Array.isArray(val)) return val.map(function (x) { return deepClone(x); });
      var out = {};
      for (var k2 in val) {
        if (Object.prototype.hasOwnProperty.call(val, k2)) out[k2] = deepClone(val[k2]);
      }
      return out;
    }
    window.structuredClone = function (val) { return deepClone(val); };
  }
})();
