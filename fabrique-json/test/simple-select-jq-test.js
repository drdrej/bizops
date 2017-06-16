/**
 * Created by asiebert on 08.06.2017.
 */
// var expect = require( 'chai' ).expect;
var assert = require('chai').assert;

describe("select for jq", function() {
    describe("simple selection", function() {

        it("handle initialization.", function( done ) {
            var select = require( '../impl/select-jq' );

            assert.isNotNull(select, "some kind of object is created");
            assert.typeOf( select, "function" );

            done();
        });
    });

    describe("invalid selections", function() {
        var select = require( '../impl/select-jq' );

        it("can handle no param call", function(done) {
            select(null, null, function( result ){
                assert.typeOf( result, "array", "return value should be an array" );
                assert.lengthOf( result, 0, "return value should be empty" );

                done();
            });
        });

        it("can handle param:what", function( done ) {
            var what = {
                name: "testObj"
            };

            select(what, null, function( result ) {
                assert.typeOf( result, "array", "return value should be an array" );
                assert.lengthOf( result, 1, "return value should have 1 object" );

                assert.include( result, what, "same same" );

                done();
            });
        });
    });

});
