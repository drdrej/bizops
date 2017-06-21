/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;

var deps = require('../impl/dependencies');


describe("Examples", function() {

        describe("basic test", function () {

            it("handle select.first.",
                function (done) {
                deps( function( ) {

                    var input = {
                        "Invoices" : [
                            {
                              value : 10
                            },
                            {
                                value : 22
                            }
                        ],
                        name: "Test"
                    };

                    load(input)
                        .process(
                            select(".Invoices").first(),
                            function ( result ) {
                               console.log("nodes selected: ");
                               console.log( result );

                               assert.typeOf( result, "object", "return value should be an object" );

                               done();
                        }
                    );
                })
            });


        });

    });



