import Vue from 'vue'
import {extend, localize, ValidationObserver, ValidationProvider} from 'vee-validate'
import fr from 'vee-validate/dist/locale/fr.json'
import * as rules from 'vee-validate/dist/rules'

export default async () => {
	Vue.component('ValidationObserver', ValidationObserver)
	Vue.component('ValidationProvider', ValidationProvider)

	localize('fr', fr)

	Object.keys(rules).forEach(rule => {
		extend(rule, rules[rule])
	})
}
