import request from '@/utils/request'

export function addFunc(data) {
  return request({
    url: '/vue-admin-template/func/',
    method: 'put',
    data
  })
}
