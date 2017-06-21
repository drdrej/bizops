/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;
var deps = require('../impl/dependencies');


describe("Examples", function () {

    describe("basic test", function () {

        it("handle select.each.transfer",
            function (done) {
                deps(function () {

                    var input = {
                        "Invoices": [
                            {
                                value: 10
                            },
                            {
                                value: 22
                            }
                        ],
                        name: "Test"
                    };

                    load(input)
                        .process(
                            select(".Invoices")
                                .each(
                                    function (node) {
                                        console.log("nodes selected: ");
                                        console.log(node);

                                        assert.typeOf(node, "object", "return value should be an object");
                                    }
                                ).transfer( function ( values ) {
                                    values.push({
                                        value: 100
                                    });
                                }
                            ),
                            function (result) {
                                console.log("process finished!");
                                console.log(result);

                                assert.typeOf(result, "array", "return value should be an array");
                                assert.lengthOf(result, 3, "nr of all selected elements");

                                done();
                            }
                        );
                })
            });


    });

});



