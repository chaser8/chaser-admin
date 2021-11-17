import store from '@/store'

function checkPermission(el, binding) {
  const { value } = binding
  const functions = store.getters && store.getters.functions

  if (value && value instanceof Array) {
    if (value.length > 0) {
      const permissionFunctions = value

      const hasPermission = functions.some(func => {
        return permissionFunctions.includes(func)
      })

      if (!hasPermission) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    }
  } else {
    throw new Error(`need roles! Like v-permission="['admin','editor']"`)
  }
}

export default {
  inserted(el, binding) {
    checkPermission(el, binding)
  },
  update(el, binding) {
    checkPermission(el, binding)
  }
}
