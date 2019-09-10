(ns texas-holdem-poker.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [texas-holdem-poker.core-test]
   [texas-holdem-poker.common-test]))

(enable-console-print!)

(doo-tests 'texas-holdem-poker.core-test
           'texas-holdem-poker.common-test)
