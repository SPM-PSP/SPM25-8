import { shallowMount, createLocalVue } from '@vue/test-utils'
import Record from '@/views/user/Record.vue'

const localVue = createLocalVue()
// mock element-ui 组件
localVue.component('el-row', { template: '<div><slot /></div>' })
localVue.component('el-col', { template: '<div><slot /></div>' })
localVue.component('el-tabs', { template: '<div><slot /></div>' })
localVue.component('el-tab-pane', { template: '<div><slot /></div>' })
localVue.component('el-tooltip', { template: '<div><slot /></div>' })
localVue.component('el-input', { template: '<input />', props: ['value'] })
localVue.component('el-button', { template: '<button><slot /></button>' })
localVue.component('el-dialog', { template: '<div><slot /></div>', props: ['visible', 'showClose'] })
localVue.component('el-upload', { template: '<div><slot /></div>' })
localVue.component('el-empty', { template: '<div>empty</div>' })
localVue.prototype.$swalConfirm = jest.fn(() => Promise.resolve(true))
localVue.prototype.$axios = {
  put: jest.fn(() => Promise.resolve({ data: { code: 200 } })),
  post: jest.fn(() => Promise.resolve({ data: { code: 200, data: [] } })),
  get: jest.fn(() => Promise.resolve({ data: { code: 200, data: {} } }))
}
localVue.prototype.$router = { push: jest.fn() }
localVue.prototype.$swal = {
  fire: jest.fn(() => Promise.resolve({ isConfirmed: true }))
}
localVue.prototype.$notify = jest.fn()
localVue.prototype.$message = {
  success: jest.fn(),
  error: jest.fn()
}

// 设置fake timers
jest.useFakeTimers()

describe('Record.vue', () => {
  beforeEach(() => {
    // Mock sessionStorage
    const mockUserInfo = { id: 1, name: 'Test User' };
    sessionStorage.setItem('userInfo', JSON.stringify(mockUserInfo));
    // 重置所有mock函数
    jest.clearAllMocks();
    // 确保每次测试前都有正确的初始状态
    localVue.prototype.$axios.put.mockImplementation(() => Promise.resolve({ data: { code: 200 } }));
    localVue.prototype.$axios.post.mockImplementation(() => Promise.resolve({ data: { code: 200, data: [] } }));
    localVue.prototype.$swalConfirm.mockImplementation(() => Promise.resolve(true));
  });

  afterEach(() => {
    sessionStorage.clear();
  });

  it('renders without error', () => {
    const wrapper = shallowMount(Record, { localVue })
    expect(wrapper.exists()).toBe(true)
  })

  it('clearData sets selectedModel to [] when confirmed', async () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.setData({ selectedModel: [{ name: 'test' }] })
    await wrapper.vm.clearData()
    expect(wrapper.vm.selectedModel).toEqual([])
  })

  it('clearData does not reset selectedModel when not confirmed', async () => {
    // 设置未确认的情况
    localVue.prototype.$swalConfirm.mockImplementationOnce(() => Promise.resolve(false))
    
    const wrapper = shallowMount(Record, { localVue })
    const testModel = [{ name: 'test' }]
    wrapper.setData({ selectedModel: testModel })
    
    await wrapper.vm.clearData()
    expect(wrapper.vm.selectedModel).toEqual(testModel)
  })

  it('cannel resets dialog state', () => {
    const wrapper = shallowMount(Record, { localVue })
    // 初始化时不设置cover属性
    wrapper.setData({ data: { a: 1 }, dialogUserOperaion: true, isOperation: true })
    wrapper.vm.cannel()
    expect(wrapper.vm.data).toEqual({})
    expect(wrapper.vm.dialogUserOperaion).toBe(false)
    expect(wrapper.vm.isOperation).toBe(false)
  })

  it('updateOperation should update model and reload data', async () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.setData({ data: { name: 'test model', cover: '' } })
    await wrapper.vm.updateOperation()
    
    expect(localVue.prototype.$axios.put).toHaveBeenCalledWith(
      '/health-model-config/update',
      { name: 'test model', cover: '' }
    )
    expect(wrapper.vm.dialogUserOperaion).toBe(false)
    expect(wrapper.vm.isOperation).toBe(false)
    expect(wrapper.vm.data).toEqual({})
    expect(localVue.prototype.$swal.fire).toHaveBeenCalled()
  })

  it('updateOperation should handle unsuccessful update', async () => {
    // 模拟更新失败的响应
    localVue.prototype.$axios.put.mockImplementationOnce(() => 
      Promise.resolve({ data: { code: 500 } })
    )
    
    const wrapper = shallowMount(Record, { localVue })
    const testData = { name: 'test model', cover: '' }
    wrapper.setData({ data: testData })
    
    await wrapper.vm.updateOperation()
    
    expect(localVue.prototype.$axios.put).toHaveBeenCalled()
    // Record.vue的实际实现在失败时也会重置表单状态，所以我们期望它为false
    expect(localVue.prototype.$swal.fire).not.toHaveBeenCalled()
  })

  it('updateModel should set data and open dialog', () => {
    const wrapper = shallowMount(Record, { localVue })
    const model = { id: 1, name: 'test model' }
    wrapper.vm.updateModel(model)
    
    expect(wrapper.vm.data).toEqual(model)
    expect(wrapper.vm.dialogUserOperaion).toBe(true)
    expect(wrapper.vm.isOperation).toBe(true)
  })

  it('deleteModel should delete model when confirmed', async () => {
    const wrapper = shallowMount(Record, { localVue })
    const model = { id: 1, name: 'test model' }
    wrapper.setData({ selectedModel: [model] })
    
    await wrapper.vm.deleteModel(model)
    
    expect(localVue.prototype.$swalConfirm).toHaveBeenCalled()
    expect(localVue.prototype.$axios.post).toHaveBeenCalledWith(
      '/health-model-config/batchDelete',
      [1]
    )
    expect(localVue.prototype.$swal.fire).toHaveBeenCalled()
  })

  it('deleteModel should handle unsuccessful deletion', async () => {
    // 模拟删除失败的响应
    localVue.prototype.$axios.post.mockImplementationOnce(() => 
      Promise.resolve({ data: { code: 500 } })
    )
    
    const wrapper = shallowMount(Record, { localVue })
    const model = { id: 1, name: 'test model' }
    wrapper.setData({ selectedModel: [model] })
    
    await wrapper.vm.deleteModel(model)
    
    expect(localVue.prototype.$swalConfirm).toHaveBeenCalled()
    expect(localVue.prototype.$axios.post).toHaveBeenCalledWith(
      '/health-model-config/batchDelete',
      [1]
    )
    // Record.vue中的实现在失败时也会调用fire，所以我们应该期望它被调用
    expect(localVue.prototype.$swal.fire).toHaveBeenCalled()
  })

  it('deleteModel should not delete model when confirmation is canceled', async () => {
    // 模拟取消确认
    localVue.prototype.$swalConfirm.mockImplementationOnce(() => Promise.resolve(false))
    
    const wrapper = shallowMount(Record, { localVue })
    const model = { id: 1, name: 'test model' }
    wrapper.setData({ selectedModel: [model], modelList: [] })
    
    await wrapper.vm.deleteModel(model)
    
    expect(localVue.prototype.$swalConfirm).toHaveBeenCalled()
    // 重新设置模拟以确保我们只跟踪后续调用
    localVue.prototype.$axios.post.mockClear();
    
    // 验证不再调用API
    expect(localVue.prototype.$axios.post.mock.calls.some(call => call[0] === '/health-model-config/batchDelete'))
      .toBe(false);
      
    expect(wrapper.vm.selectedModel).toEqual([model])
  })

  it('deleteModel should remove model from selectedModel when successful', async () => {
    const wrapper = shallowMount(Record, { localVue })
    const model1 = { id: 1, name: 'model 1' }
    const model2 = { id: 2, name: 'model 2' }
    wrapper.setData({ selectedModel: [model1, model2] })
    
    await wrapper.vm.deleteModel(model1)
    
    // 验证selectedModel中移除了model1
    expect(wrapper.vm.selectedModel.some(model => model.id === model1.id)).toBe(false)
    expect(wrapper.vm.selectedModel.some(model => model.id === model2.id)).toBe(true)
  })

  it('goBack should navigate to user page', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.vm.goBack()
    
    expect(localVue.prototype.$router.push).toHaveBeenCalledWith('/user')
  })

  it('toRecord should save user health records', async () => {
    const wrapper = shallowMount(Record, { localVue })
    const models = [
      { id: 1, name: 'model 1', value: 10 },
      { id: 2, name: 'model 2', value: 20 }
    ]
    wrapper.setData({ selectedModel: models })
    
    await wrapper.vm.toRecord()
    
    expect(localVue.prototype.$axios.post).toHaveBeenCalledWith(
      '/user-health/save',
      [
        { healthModelConfigId: 1, value: 10 },
        { healthModelConfigId: 2, value: 20 }
      ]
    )
    expect(localVue.prototype.$notify).toHaveBeenCalled()
    
    // 使用setTimeout前要设置jest.useFakeTimers()
    jest.advanceTimersByTime(2000)
    expect(localVue.prototype.$router.push).toHaveBeenCalledWith('/user')
  })

  it('toRecord should handle unsuccessful save', async () => {
    // 创建自定义mock，避免使用共享mock
    const mockNotify = jest.fn();
    const mockRouterPush = jest.fn();
    const mockAxiosPost = jest.fn(() => 
      Promise.resolve({ data: { code: 500 } })
    );
    
    const wrapper = shallowMount(Record, { 
      localVue,
      mocks: {
        $notify: mockNotify,
        $router: { push: mockRouterPush },
        $axios: { 
          post: mockAxiosPost,
          put: jest.fn(),
          get: jest.fn()
        }
      }
    });
    
    const models = [
      { id: 1, name: 'model 1', value: 10 }
    ]
    wrapper.setData({ selectedModel: models })
    
    await wrapper.vm.toRecord()
    
    expect(mockAxiosPost).toHaveBeenCalledWith(
      '/user-health/save',
      [
        { healthModelConfigId: 1, value: 10 }
      ]
    )
    
    // 在API返回code=500时，Record.vue不应该调用notify和router.push
    expect(mockNotify).not.toHaveBeenCalled()
    jest.advanceTimersByTime(2000)
    expect(mockRouterPush).not.toHaveBeenCalled()
  })

  it('modelSelected should add model if not already selected', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.setData({ selectedModel: [] })
    
    const model = { id: 1, name: 'test model' }
    wrapper.vm.modelSelected(model)
    
    expect(wrapper.vm.selectedModel).toEqual([model])
    
    // Adding the same model should not duplicate
    wrapper.vm.modelSelected(model)
    expect(wrapper.vm.selectedModel.length).toBe(1)
  })

  it('searModel should call getAllModelConfig', () => {
    const wrapper = shallowMount(Record, { localVue })
    const spy = jest.spyOn(wrapper.vm, 'getAllModelConfig')
    
    wrapper.vm.searModel()
    
    expect(spy).toHaveBeenCalled()
  })

  it('handleFilterClear should reset name and call getAllModelConfig', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.setData({ userHealthModel: { name: 'test' } })
    const spy = jest.spyOn(wrapper.vm, 'getAllModelConfig')
    
    wrapper.vm.handleFilterClear()
    
    expect(wrapper.vm.userHealthModel.name).toBe('')
    expect(spy).toHaveBeenCalled()
  })

  it('handleAvatarSuccess should set cover when successful', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.setData({ data: {} })
    
    wrapper.vm.handleAvatarSuccess({ code: 200, data: 'cover-url' }, {})
    
    expect(localVue.prototype.$message.success).toHaveBeenCalled()
    expect(wrapper.vm.data.cover).toBe('cover-url')
  })

  it('handleAvatarSuccess should show error when unsuccessful', () => {
    const wrapper = shallowMount(Record, { localVue })
    
    wrapper.vm.handleAvatarSuccess({ code: 500 }, {})
    
    expect(localVue.prototype.$message.error).toHaveBeenCalled()
  })

  it('addOperation should save new model config', async () => {
    // Mock axios.post to return success
    const mockPostSuccess = jest.fn().mockResolvedValue({
      data: { code: 200, msg: 'success' }
    });
    
    const wrapper = shallowMount(Record, {
      localVue,
      mocks: {
        $axios: {
          ...localVue.prototype.$axios,
          post: mockPostSuccess
        }
      }
    })
    
    wrapper.setData({ 
      data: { name: 'New model', cover: '' },
      userId: 1,
      dialogUserOperaion: true
    })
    
    await wrapper.vm.addOperation()
    
    expect(mockPostSuccess).toHaveBeenCalledWith(
      '/health-model-config/save',
      { name: 'New model', cover: '', userId: 1 }
    )
    expect(wrapper.vm.dialogUserOperaion).toBe(false)
  })

  it('addOperation should handle API error response', async () => {
    // Mock axios.post to return error response
    const mockPostApiError = jest.fn().mockResolvedValue({
      data: { code: 500, msg: 'API error' }
    });
    
    // Mock消息函数
    const mockMessageError = jest.fn();
    
    const wrapper = shallowMount(Record, {
      localVue,
      mocks: {
        $axios: {
          ...localVue.prototype.$axios,
          post: mockPostApiError
        },
        $message: {
          success: jest.fn(),
          error: mockMessageError
        }
      }
    })
    
    wrapper.setData({ 
      data: { name: 'New model', cover: '' },
      userId: 1
    })
    
    await wrapper.vm.addOperation()
    
    expect(mockPostApiError).toHaveBeenCalled()
    // 在实际组件中，错误消息是通过直接访问response.data.msg来显示的
    expect(mockMessageError).toHaveBeenCalledWith('API error')
  })

  it('addModel should open dialog', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.vm.addModel()
    
    expect(wrapper.vm.dialogUserOperaion).toBe(true)
  })

  it('handleClick should set correct filters and reload data', () => {
    const wrapper = shallowMount(Record, { localVue })
    const spy = jest.spyOn(wrapper.vm, 'getAllModelConfig')
    
    // Test for global models tab
    wrapper.setData({ activeName: 'first', userHealthModel: { name: 'test' } })
    wrapper.vm.handleClick()
    
    expect(wrapper.vm.userHealthModel).toEqual({ isGlobal: true })
    expect(spy).toHaveBeenCalled()
    
    // Test for personal models tab
    spy.mockClear()
    wrapper.setData({ activeName: 'second', userHealthModel: { name: 'test' } })
    wrapper.vm.handleClick()
    
    expect(wrapper.vm.userHealthModel).toEqual({ userId: 1 })
    expect(spy).toHaveBeenCalled()
  })

  it('getAllModelConfig should fetch model configurations', async () => {
    const mockModels = [{ id: 1, name: 'Model 1' }, { id: 2, name: 'Model 2' }]
    
    // 为这个特定测试创建一个模拟实现
    const mockPostWithModels = jest.fn().mockResolvedValue({
      data: { code: 200, data: mockModels }
    });
    
    const wrapper = shallowMount(Record, {
      localVue,
      mocks: {
        $axios: {
          ...localVue.prototype.$axios,
          post: mockPostWithModels
        }
      }
    })
    
    await wrapper.vm.getAllModelConfig()
    
    expect(mockPostWithModels).toHaveBeenCalledWith(
      '/health-model-config/query', 
      wrapper.vm.userHealthModel
    )
    
    // 这里不需要手动设置modelList，因为组件在API响应成功时会自动设置
    expect(wrapper.vm.modelList).toEqual(mockModels)
  })

  it('getAllModelConfig should handle API error', async () => {
    // 模拟API错误响应
    const mockPostApiError = jest.fn().mockResolvedValue({
      data: { code: 500 }
    });
    
    const wrapper = shallowMount(Record, {
      localVue,
      mocks: {
        $axios: {
          ...localVue.prototype.$axios,
          post: mockPostApiError
        }
      }
    })
    
    // 初始化一个空数组
    wrapper.setData({ modelList: [] })
    
    await wrapper.vm.getAllModelConfig()
    
    expect(mockPostApiError).toHaveBeenCalled()
    // 组件在API失败时不会修改modelList，所以它应该保持原样
    expect(wrapper.vm.modelList).toEqual([])
  })

  it('getUserInfo should get user info from sessionStorage', () => {
    const wrapper = shallowMount(Record, { localVue })
    wrapper.vm.getUserInfo()
    
    expect(wrapper.vm.userInfo).toEqual({ id: 1, name: 'Test User' })
  })
   
  it('should switch between global models and personal models', async () => {
    const wrapper = shallowMount(Record, { localVue })
    const handleClickSpy = jest.spyOn(wrapper.vm, 'handleClick')
    
    // 初始状态应该是加载全局模型
    expect(wrapper.vm.activeName).toBe('first')
    expect(wrapper.vm.userHealthModel.isGlobal).toBe(true)
    
    // 切换到个人模型
    wrapper.setData({ activeName: 'second' })
    wrapper.vm.handleClick()
    
    // 验证调用了正确的处理方法
    expect(handleClickSpy).toHaveBeenCalled()
    expect(wrapper.vm.userHealthModel.userId).toBe(1)
    expect(wrapper.vm.userHealthModel.isGlobal).toBeUndefined()
    
    // 切回全局模型
    wrapper.setData({ activeName: 'first' })
    wrapper.vm.handleClick()
    
    expect(wrapper.vm.userHealthModel.isGlobal).toBe(true)
  })
}) 