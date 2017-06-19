/**
 * Created by asiebert on 08.06.2017.
 */
var select = require( "./select-jq" );
var load = require('load-json-file');

var Defaults = {
    first : function() {
        return {};
    },

    each : function() {
        return {};
    },

    all : function () {
        return [];
    }

};

var Results = {
    first : function( selected ) {
        return selected[0];
    },

    each : function( selected ) {
        //return selected[0];
        throw "not impl! todo!!!";
    },

    all : function (selected) {
        return selected;
    }

};

module.exports = function( where ) {
    var _ = require( "underscore" );
    var callback = require( './callback' );
    // var S = require( "string" );

    return {
        query : where,
        quantor : 'all',

        all: function( ) {
            this.quantor = 'all';
            return this;
        },

        each: function () {
            this.quantor = 'each';
            return this;
        },

        first: function() {
            this.quantor = 'first';
            return this;
        },

        _prepareThen: function( fnc ) {
            var q = this.quantor;
            return function( selected ) {
                var _ = require( 'underscore' );

                if( _.isNull(selected) ) {
                    return Defaults[q]();
                }

                var result = Results[q](selected);

                fnc( result );
            };
        },

        process : function( input, ifOk, error ) {
            var then = this._prepareThen(ifOk);

            if( _.isUndefined( where ) ) {
                callback(then).exec(input);
                return;
            }

            if( _.isString( where ) ) {
                select( input, where, then );
                return;
            }

            if( _.isFunction( where ) ) {
                callback(then).exec( where(input) );
                return;
            }

            if( _.isObject( where ) ) {
                    var src = null;

                    if( _.has( where, "file" ) ) {
                        src = load.sync( where.file );
                    } else {
                        src = load.sync( input );
                    }

                    if( _.has( where, "query" ) ) {
                        select( src, where.query, then);
                        return;
                    } else {
                        callback(then).exec( src );
                        return;
                    }
            }

            callback( error ).exec( "Can't understand where-Clause: " + where );
        }
    };
};