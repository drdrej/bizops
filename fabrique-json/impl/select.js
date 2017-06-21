/**
 * Created by asiebert on 08.06.2017.
 */
var select = require("./select-jq");
var load = require('load-json-file');

var Defaults = {
    first: function () {
        return {};
    },

    each: function ( ) {
        return {};
    },

    all: function () {
        return [];
    }

};

var Results = {
    /**
     *
     * @param selected
     * @param cb ignore this param
     * @param done
     */
    first: function (selected, cb, done) {
        done( selected[0] );
    },

    each: function ( selected, cb, done ) {
        var _a = require('async');
        _a.forEach(
                selected,
                function (item, cb) {
                    cb();
                }, function (err) {
                    console.log("[done!]");
                    console.log(err);

                    done( selected );
                });
    },

    /**
     *
     * @param selected
     * @param cb ignores
     * @param done
     */
    all: function (selected, cb, done) {
        done(selected);
    }

};

module.exports = function (where) {
    var _ = require("underscore");
    var callback = require('./callback');
    // var S = require( "string" );

    return {
        query: where,
        quantor: 'all',
        quantorCallback: function() {
            // noop with no this ref
        },

        all: function () {
            this.quantor = 'all';
            return this;
        },

        each: function ( qFnc ) {
            this.quantor = 'each';
            this.quantorCallback = qFnc;

            return this;
        },

        first: function () {
            this.quantor = 'first';
            return this;
        },

        transfer: function( factory )  {
            var transfer = require( './transfer' );

            transfer( factory );
            return this;
        },

        _prepareThen: function (fnc) {
            var q = this.quantor;
            var qFnc = this.quantorCallback;

            return function (selected) {
                var _ = require('underscore');

                if (_.isNull(selected)) {
                    return Defaults[q]();
                }

                var handler = Results[q];

                if (!_.isFunction(handler)) {
                    fnc(selected);
                    console.log( "quantor-resulst supports only functions. exec done(selected)" );
                    return ;
                }

                handler(selected, qFnc, fnc);
            };
        },

        process: function (input, ifOk, error) {
            var then = this._prepareThen(ifOk);

            if (_.isUndefined(where)) {
                callback(then).exec(input);
                return;
            }

            if (_.isString(where)) {
                select(input, where, then);
                return;
            }

            if (_.isFunction(where)) {
                callback(then).exec(where(input));
                return;
            }

            if (_.isObject(where)) {
                var src = null;

                if (_.has(where, "file")) {
                    src = load.sync(where.file);
                } else {
                    src = load.sync(input);
                }

                if (_.has(where, "query")) {
                    select(src, where.query, then);
                    return;
                } else {
                    callback(then).exec(src);
                    return;
                }
            }

            callback(error).exec("Can't understand where-Clause: " + where);
        }
    };
};