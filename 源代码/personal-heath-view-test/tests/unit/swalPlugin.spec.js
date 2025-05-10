import swalPlugin from '@/utils/swalPlugin'
import Swal from 'sweetalert2'

jest.mock('sweetalert2', () => ({
  fire: jest.fn(() => Promise.resolve({ isConfirmed: true }))
}))

describe('swalPlugin', () => {
  it('should install and add $swalConfirm to Vue prototype', () => {
    const Vue = function() {};
    Vue.prototype = {};
    swalPlugin.install(Vue);
    expect(Vue.prototype.$swalConfirm).toBeDefined();
  });

  it('should call Swal.fire and return true when confirmed', async () => {
    const Vue = function() {};
    Vue.prototype = {};
    swalPlugin.install(Vue);
    const result = await Vue.prototype.$swalConfirm({ title: 'test' });
    expect(Swal.fire).toHaveBeenCalledWith(expect.objectContaining({ title: 'test' }));
    expect(result).toBe(true);
  });

  it('should return false when Swal.fire throws error', async () => {
    Swal.fire.mockImplementationOnce(() => { throw new Error('fail') });
    const Vue = function() {};
    Vue.prototype = {};
    swalPlugin.install(Vue);
    const result = await Vue.prototype.$swalConfirm({ title: 'fail' });
    expect(result).toBe(false);
  });
}); 